package com.welovecoding;

import com.welovecoding.data.user.entity.ExampleUserDetails;
import com.welovecoding.data.user.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

  public static void logInUser(User user) {
    ExampleUserDetails userDetails = ExampleUserDetails.getBuilder()
      .firstName(user.getFirstName())
      .id(user.getId())
      .lastName(user.getLastName())
      .password(user.getPassword())
      .role(user.getRole())
      .socialSignInProvider(user.getSignInProvider())
      .username(user.getEmail())
      .build();

    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
