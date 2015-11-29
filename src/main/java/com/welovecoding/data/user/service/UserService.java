package com.welovecoding.data.user.service;

import com.welovecoding.data.user.repository.AuthorityRepository;
import com.welovecoding.data.user.repository.UserRepository;
import com.welovecoding.data.user.entity.User;
import com.welovecoding.data.user.entity.Authority;
import com.welovecoding.config.security.util.SecurityUtils;
import com.welovecoding.data.user.util.RandomUtil;
import java.time.ZonedDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;
import javax.annotation.PostConstruct;

@Service
@Transactional
public class UserService {

	private final Logger log = LoggerFactory.getLogger(UserService.class);

	@Inject
	private PasswordEncoder passwordEncoder;

	@Inject
	private UserRepository userRepository;

	@Inject
	private AuthorityRepository authorityRepository;

	@PostConstruct
	private void createAuthorities() {
		if (null == authorityRepository.findOne("ROLE_USER")) {
			log.debug("Creating ROLE_USER Authority");
			Authority userRole = new Authority();
			userRole.setName("ROLE_USER");
			authorityRepository.save(userRole);
		}
		if (null == authorityRepository.findOne("ROLE_ADMIN")) {
			log.debug("Creating ROLE_ADMIN Authority");
			Authority adminRole = new Authority();
			adminRole.setName("ROLE_ADMIN");
			authorityRepository.save(adminRole);
		}
	}

	public Optional<User> activateRegistration(String key) {
		log.debug("Activating user for activation key {}", key);
		userRepository.findOneByActivationKey(key)
			.map(user -> {
				// activate given user for the registration key.
				user.setActivated(true);
				user.setActivationKey(null);
				userRepository.save(user);
				log.debug("Activated user: {}", user);
				return user;
			});
		return Optional.empty();
	}

	public Optional<User> completePasswordReset(String newPassword, String key) {
		log.debug("Reset user password for reset key {}", key);

		return userRepository.findOneByResetKey(key)
			.filter(user -> {
				ZonedDateTime oneDayAgo = ZonedDateTime.now().minusHours(24);
				return user.getResetDate().isAfter(oneDayAgo);
			})
			.map(user -> {
				user.setPassword(passwordEncoder.encode(newPassword));
				user.setResetKey(null);
				user.setResetDate(null);
				userRepository.save(user);
				return user;
			});
	}

	public Optional<User> requestPasswordReset(String mail) {
		return userRepository.findOneByEmail(mail)
			.filter(user -> user.getActivated())
			.map(user -> {
				user.setResetKey(RandomUtil.generateResetKey());
				user.setResetDate(ZonedDateTime.now());
				userRepository.save(user);
				return user;
			});
	}

	public User createUserInformation(String login, String password, String firstName, String lastName, String email,
		String langKey) {

		User newUser = new User();
		Authority authority = authorityRepository.findOne("ROLE_USER");
		Set<Authority> authorities = new HashSet<>();
		String encryptedPassword = passwordEncoder.encode(password);
		newUser.setLogin(login);
		// new user gets initially a generated password
		newUser.setPassword(encryptedPassword);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(email);
		newUser.setLangKey(langKey);
		// new user is not active
		newUser.setActivated(false);
		// new user gets registration key
		newUser.setActivationKey(RandomUtil.generateActivationKey());
		authorities.add(authority);
		newUser.setAuthorities(authorities);
		userRepository.save(newUser);
		log.debug("Created Information for User: {}", newUser);
		return newUser;
	}

	public void updateUserInformation(String firstName, String lastName, String email, String langKey) {
		userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(u -> {
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setEmail(email);
			u.setLangKey(langKey);
			userRepository.save(u);
			log.debug("Changed Information for User: {}", u);
		});
	}

	public void changePassword(String password) {
		userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(u -> {
			String encryptedPassword = passwordEncoder.encode(password);
			u.setPassword(encryptedPassword);
			userRepository.save(u);
			log.debug("Changed password for User: {}", u);
		});
	}

	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthoritiesByLogin(String login) {
		return userRepository.findOneByLogin(login).map(u -> {
			u.getAuthorities().size();
			return u;
		});
	}

	@Transactional(readOnly = true)
	public User getUserWithAuthorities(Long id) {
		User user = userRepository.findOne(id);
		user.getAuthorities().size(); // eagerly load the association
		return user;
	}

	@Transactional(readOnly = true)
	public User getUserWithAuthorities() {
		User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get();
		user.getAuthorities().size(); // eagerly load the association
		return user;
	}

	/**
	 * Not activated users should be automatically deleted after 3 days.
	 * <p/>
	 * <p>
	 * This is scheduled to get fired everyday, at 01:00 (am).
	 * </p>
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void removeNotActivatedUsers() {
		ZonedDateTime now = ZonedDateTime.now();
		List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(now.minusDays(3));
		for (User user : users) {
			log.debug("Deleting not activated user {}", user.getLogin());
			userRepository.delete(user);
		}
	}
}
