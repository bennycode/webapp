package com.welovecoding.api.v1.user;


import com.welovecoding.api.v1.base.SlugBaseDTO;

import java.util.Date;

public class UserDTO extends SlugBaseDTO<Long> {

  public UserDTO(Long identifier, Date created, Date lastModified, String slug) {
    super(identifier, created, lastModified, slug);
  }
}
