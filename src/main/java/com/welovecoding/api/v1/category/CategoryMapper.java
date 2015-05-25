package com.welovecoding.api.v1.category;


import com.welovecoding.api.v1.tutorial.TutorialMapper;
import com.welovecoding.data.category.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

  public static CategoryDTO entityToDto(Category entity, int dept) {
    if (entity == null || dept == 0) {
      return null;
    }
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }
    CategoryDTO dto = new CategoryDTO(
      entity.getId(),
      entity.getCreated(),
      entity.getLastModified(),
      entity.getSlug(),
      entity.getTitle(),
      entity.getColor(),
      TutorialMapper.entityListToDtoList(entity.getTutorials(), dept)
    );
    return dto;
  }

  public static List<CategoryDTO> entityListToDtoList(List<Category> entityList, int dept) {
    List<CategoryDTO> dtoList = new ArrayList<>();
    if (dept > 0 && entityList != null) {
      for (Category entity : entityList) {
        dtoList.add(entityToDto(entity, dept));
      }
    }
    return dtoList;
  }

}
