package com.welovecoding.data.user.service;

import com.welovecoding.data.user.entity.User;
import com.welovecoding.data.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;


  private boolean emailExist(String email) {
    User user = repository.findByEmail(email);

    if (user != null) {
      return true;
    }

    return false;
  }

}
