package com.welovecoding.data.user.service;

import com.welovecoding.data.user.entity.ExampleUserDetails;
import com.welovecoding.data.user.entity.User;
import com.welovecoding.data.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class RepositoryUserDetailsService implements UserDetailsService {

  private UserRepository repository;

  @Autowired
  public RepositoryUserDetailsService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public ExampleUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repository.findByEmail(username);

    if (user == null) {
      throw new UsernameNotFoundException("No userDetails found with username: " + username);
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
