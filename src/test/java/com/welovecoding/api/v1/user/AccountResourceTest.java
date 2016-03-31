package com.welovecoding.api.v1.user;

import com.welovecoding.Application;
import com.welovecoding.api.v1.user.dto.UserDTO;
import com.welovecoding.config.security.util.AuthoritiesConstants;
import com.welovecoding.data.mail.MailService;
import com.welovecoding.data.user.entity.Authority;
import com.welovecoding.data.user.entity.User;
import com.welovecoding.data.user.repository.AuthorityRepository;
import com.welovecoding.data.user.repository.UserRepository;
import com.welovecoding.data.user.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Rule;
import org.junit.rules.TestName;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class AccountResourceTest {

    private static final Logger log = LoggerFactory.getLogger(AccountResourceTest.class.getName());

    @Rule
    public TestName name = new TestName();

    @Inject
    private ApplicationContext applicationContext;

    @Inject
    private UserRepository userRepository;

    @Inject
    private AuthorityRepository authorityRepository;

    @Inject
    private UserService userService;

    @Mock
    private UserService mockUserService;

    @Mock
    private MailService mockMailService;

    private MockMvc restUserMockMvc;

    private MockMvc restMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        doNothing().when(mockMailService).sendActivationEmail((User) anyObject(), anyString());

        AccountResource accountResource = new AccountResource();
        ReflectionTestUtils.setField(accountResource, "userRepository", userRepository);
        ReflectionTestUtils.setField(accountResource, "userService", userService);
        ReflectionTestUtils.setField(accountResource, "mailService", mockMailService);

        AccountResource accountUserMockResource = new AccountResource();
        ReflectionTestUtils.setField(accountUserMockResource, "userRepository", userRepository);
        ReflectionTestUtils.setField(accountUserMockResource, "userService", mockUserService);
        ReflectionTestUtils.setField(accountUserMockResource, "mailService", mockMailService);

        this.restMvc = MockMvcBuilders.standaloneSetup(accountResource).build();
        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(accountUserMockResource).build();
    }

    @Test
    public void testNonAuthenticatedUser() throws Exception {
        log.debug(name.getMethodName());
        restUserMockMvc.perform(get("/api/v1/account/authenticate").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testAuthenticatedUser() throws Exception {
        log.debug(name.getMethodName());
        restUserMockMvc.perform(get("/api/v1/account/authenticate").with(request -> {
            request.setRemoteUser("test");
            return request;
        })
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("test"));
    }

    @Test
    public void testGetExistingAccount() throws Exception {
        log.debug(name.getMethodName());
        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority();
        authority.setName(AuthoritiesConstants.ADMIN);
        authorities.add(authority);

        User user = new User();
        user.setLogin("test");
        user.setFirstName("john");
        user.setLastName("doe");
        user.setEmail("john.doe@mail.com");
        user.setAuthorities(authorities);
        when(mockUserService.getUserWithAuthorities()).thenReturn(user);

        restUserMockMvc.perform(get("/api/v1/account").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.login").value("test"))
                .andExpect(jsonPath("$.firstName").value("john"))
                .andExpect(jsonPath("$.lastName").value("doe"))
                .andExpect(jsonPath("$.email").value("john.doe@mail.com"))
                .andExpect(jsonPath("$.authorities").value(AuthoritiesConstants.ADMIN));
    }

    @Test
    public void testGetUnknownAccount() throws Exception {
        log.debug(name.getMethodName());
        when(mockUserService.getUserWithAuthorities()).thenReturn(null);

        restUserMockMvc.perform(get("/api/v1/account").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @Transactional
    public void testRegisterValid() throws Exception {
        log.debug(name.getMethodName());
        UserDTO u = new UserDTO(
                "joe", // login
                "password", // password
                "Joe", // firstName
                "Shmoe", // lastName
                "joe@example.com", // e-mail
                true, // activated
                "en", // langKey
                new HashSet<>(Arrays.asList(AuthoritiesConstants.USER))
        );

        restMvc.perform(
                post("/api/v1/account/register").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.
                convertObjectToJsonBytes(u)))
                .andExpect(status().isCreated());

        Optional<User> user = userRepository.findOneByLogin("joe");
        assertThat(user.isPresent()).isTrue();
    }

    @Test
    @Transactional
    public void testRegisterInvalidLogin() throws Exception {
        log.debug(name.getMethodName());
        UserDTO u = new UserDTO(
                "funky-log!n", // login <-- invalid
                "password", // password
                "Funky", // firstName
                "One", // lastName
                "funky@example.com", // e-mail
                true, // activated
                "en", // langKey
                new HashSet<>(Arrays.asList(AuthoritiesConstants.USER))
        );

        restUserMockMvc.perform(
                post("/api/v1/account/register").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.
                convertObjectToJsonBytes(u)))
                .andExpect(status().isBadRequest());

        Optional<User> user = userRepository.findOneByEmail("funky@example.com");
        assertThat(user.isPresent()).isFalse();
    }

    @Test
    @Transactional
    public void testRegisterInvalidEmail() throws Exception {
        log.debug(name.getMethodName());
        UserDTO u = new UserDTO(
                "bob", // login
                "password", // password
                "Bob", // firstName
                "Green", // lastName
                "invalid", // e-mail <-- invalid
                true, // activated
                "en", // langKey
                new HashSet<>(Arrays.asList(AuthoritiesConstants.USER))
        );

        restUserMockMvc.perform(
                post("/api/v1/account/register").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.
                convertObjectToJsonBytes(u)))
                .andExpect(status().isBadRequest());

        Optional<User> user = userRepository.findOneByLogin("bob");
        assertThat(user.isPresent()).isFalse();
    }

    @Test
    @Transactional
    public void testRegisterDuplicateLogin() throws Exception {
        log.debug(name.getMethodName());
        // Good
        UserDTO u = new UserDTO(
                "alice", // login
                "password", // password
                "Alice", // firstName
                "Something", // lastName
                "alice@example.com", // e-mail
                true, // activated
                "en", // langKey
                new HashSet<>(Arrays.asList(AuthoritiesConstants.USER))
        );

        // Duplicate login, different e-mail
        UserDTO dup = new UserDTO(u.getLogin(), u.getPassword(), u.getLogin(), u.getLastName(),
                "alicejr@example.com", true, u.getLangKey(), u.getAuthorities());

        // Good user
        restMvc.perform(
                post("/api/v1/account/register").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.
                convertObjectToJsonBytes(u)))
                .andExpect(status().isCreated());

        // Duplicate login
        restMvc.perform(
                post("/api/v1/account/register").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.
                convertObjectToJsonBytes(dup)))
                .andExpect(status().is4xxClientError());

        Optional<User> userDup = userRepository.findOneByEmail("alicejr@example.com");
        assertThat(userDup.isPresent()).isFalse();
    }

    @Test
    @Transactional
    public void testRegisterDuplicateEmail() throws Exception {
        log.debug(name.getMethodName());
        // Good
        UserDTO u = new UserDTO(
                "john", // login
                "password", // password
                "John", // firstName
                "Doe", // lastName
                "john@example.com", // e-mail
                true, // activated
                "en", // langKey
                new HashSet<>(Arrays.asList(AuthoritiesConstants.USER))
        );

        // Duplicate e-mail, different login
        UserDTO dup = new UserDTO("johnjr", u.getPassword(), u.getLogin(), u.getLastName(),
                u.getEmail(), true, u.getLangKey(), u.getAuthorities());

        // Good user
        restMvc.perform(
                post("/api/v1/account/register").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.
                convertObjectToJsonBytes(u)))
                .andExpect(status().isCreated());

        // Duplicate e-mail
        restMvc.perform(
                post("/api/v1/account/register").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.
                convertObjectToJsonBytes(dup)))
                .andExpect(status().is4xxClientError());

        Optional<User> userDup = userRepository.findOneByLogin("johnjr");
        assertThat(userDup.isPresent()).isFalse();
    }

    @Test
    @Transactional
    public void testRegisterAdminIsIgnored() throws Exception {
        log.debug(name.getMethodName());
        UserDTO u = new UserDTO(
                "badguy", // login
                "password", // password
                "Bad", // firstName
                "Guy", // lastName
                "badguy@example.com", // e-mail
                true, // activated
                "en", // langKey
                new HashSet<>(Arrays.asList(AuthoritiesConstants.ADMIN)) // <-- only admin should be able to do that
        );

        restMvc.perform(
                post("/api/v1/account/register").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.
                convertObjectToJsonBytes(u)))
                .andExpect(status().isCreated());

        Optional<User> userDup = userRepository.findOneByLogin("badguy");
        assertThat(userDup.isPresent()).isTrue();
        assertThat(userDup.get().getAuthorities()).hasSize(1)
                .containsExactly(authorityRepository.findOne(AuthoritiesConstants.USER));
    }
}
