package com.welovecoding.api.v1.tutorial;


import com.welovecoding.data.tutorial.entity.Difficulty;
import com.welovecoding.data.tutorial.entity.LanguageCode;
import com.welovecoding.data.tutorial.entity.Tutorial;

import java.util.ArrayList;
import java.util.List;

public class TutorialMapper {

  public static TutorialDTO entityToDto(Tutorial entity, int dept) {
    if (entity == null || dept == 0) {
      return null;
    }
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }
    TutorialDTO dto = new TutorialDTO(
      entity.getId(),
      entity.getCreated(),
      entity.getLastModified(),
      entity.getSlug(),
      entity.getTitle(),
      entity.getDescription(),
      entity.isEnabled(),
      AuthorMapper.entityListToDtoList(
        entity.getAuthors(),
        dept),
      LanguageCode.EN,
      Difficulty.EASY
    );
    return dto;
  }

  public static List<TutorialDTO> entityListToDtoList(List<Tutorial> entityList, int dept) {
    List<TutorialDTO> dtoList = new ArrayList<>();
    if (dept > 0 && entityList != null) {
      for (Tutorial entity : entityList) {
        dtoList.add(entityToDto(entity, dept));
      }
    }
    return dtoList;
  }

}
