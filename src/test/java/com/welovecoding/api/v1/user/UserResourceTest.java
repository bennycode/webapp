package com.welovecoding.api.v1.user;

import com.welovecoding.Application;
import com.welovecoding.data.user.entity.User;
import com.welovecoding.data.user.repository.UserRepository;
import com.welovecoding.data.user.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.inject.Inject;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserResource REST controller.
 *
 * @see UserResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class UserResourceTest {

	private static final Logger log = LoggerFactory.getLogger(AccountResourceTest.class.getName());

	@Rule
	public TestName name = new TestName();

	@Inject
	private UserRepository userRepository;

	@Inject
	private UserService userService;

	private MockMvc restUserMockMvc;

	@Before
	public void setup() {
		UserResource userResource = new UserResource();
		ReflectionTestUtils.setField(userResource, "userRepository", userRepository);
		ReflectionTestUtils.setField(userResource, "userService", userService);
		this.restUserMockMvc = MockMvcBuilders.standaloneSetup(userResource).build();
	}

	@Test
	public void testGetExistingUser() throws Exception {
		log.debug(name.getMethodName());
		User admin = userService.createUserInformation("admin", "password", "Administrator", "Administrator", "admin@wlc.com", "de");
		userService.activateRegistration(admin.getActivationKey());

		restUserMockMvc.perform(get("/api/v1/users/admin").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.lastName").value("Administrator"));
	}

	@Test
	public void testGetUnknownUser() throws Exception {
		log.debug(name.getMethodName());
		restUserMockMvc.perform(get("/api/v1/users/unknown").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}
}
