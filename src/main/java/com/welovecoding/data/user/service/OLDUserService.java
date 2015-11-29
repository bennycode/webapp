package com.welovecoding.data.user.service;

import com.welovecoding.data.user.repository.UserRepository;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class OLDUserService {

  @Inject
  private UserRepository repository;

}
