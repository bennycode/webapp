package com.welovecoding.data.user.service;

import com.welovecoding.data.user.entity.ExampleUserDetails;
import com.welovecoding.data.user.entity.User;
import com.welovecoding.data.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ExampleUserDetailsService implements UserDetailsService {


  private UserRepository repository;

  @Autowired
  public ExampleUserDetailsService(UserRepository repository) {
    this.repository = repository;
  }

  /**
   * Loads the user information.
   *
   * @param username The username of the requested user.
   * @return The information of the user.
   * @throws UsernameNotFoundException Thrown if no user is found with the given username.
   */
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = repository.findByEmail(username);

    if (user == null) {
      throw new UsernameNotFoundException("No user found with username: " + username);
    }

    ExampleUserDetails principal = ExampleUserDetails.getBuilder()
      .firstName(user.getFirstName())
      .id(user.getId())
      .lastName(user.getLastName())
      .password(user.getPassword())
      .role(user.getRole())
      .socialSignInProvider(user.getSignInProvider())
      .username(user.getEmail())
      .build();


    return principal;
  }
}
