package com.welovecoding.api.v1.tutorial;


import com.welovecoding.data.tutorial.entity.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorMapper {

  public static AuthorDTO entityToDto(Author entity, int dept) {
    if (entity == null || dept == 0) {
      return null;
    }
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }
    return null;
  }

  public static List<AuthorDTO> entityListToDtoList(List<Author> entityList, int dept) {
    List<AuthorDTO> dtoList = new ArrayList<>();
    if (dept > 0 && entityList != null) {
      for (Author entity : entityList) {
        dtoList.add(entityToDto(entity, dept));
      }
    }
    return dtoList;
  }

}
