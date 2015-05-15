package com.welovecoding.api.v1.category;


import com.welovecoding.api.v1.playlist.PlaylistDTO;
import com.welovecoding.api.v1.playlist.PlaylistMapper;
import com.welovecoding.data.category.Category;
import com.welovecoding.data.playlist.entity.LanguageCode;
import com.welovecoding.data.playlist.entity.Playlist;

import java.util.*;

public class CategoryMapper {

  public static CategoryDTO entityToDto(Category entity, int dept) {
    if (entity == null || dept == 0) {
      return null;
    }
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }
    Set<PlaylistDTO> entitySetToDtoSet = new HashSet<>();
    dept--;
    if (entity.getPlaylists() != null) {
      entitySetToDtoSet = PlaylistMapper.entitySetToDtoSet(entity.getPlaylists(), dept);
    }

    CategoryDTO dto = new CategoryDTO();

    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setColor(entity.getColor());

    HashMap<String, String> availableLanguagesMap = new HashMap<>();
    List<String> availableLanguages = new ArrayList<>();
    int numberOfVideos = 0;

    if (entity.getPlaylists().size() > 0) {
      for (Playlist playlist : entity.getPlaylists()) {
        numberOfVideos += playlist.getVideos().size();
//        availableLanguagesMap.put(
//          mapLanguage(playlist.getLanguageCode()),
//          mapLanguage(playlist.getLanguageCode())
//        );
      }
    }

    dto.setNumberOfVideos(numberOfVideos);

    SortedSet<String> sortedKeys = new TreeSet<>(availableLanguagesMap.keySet());
    for (String key : sortedKeys) {
      availableLanguages.add(key);
    }

    dto.setAvailableLanguages(availableLanguages);
    return dto;
  }

  public static Set<CategoryDTO> entitySetToDtoSet(Set<Category> entityList, int dept) {
    Set<CategoryDTO> dtoList = new HashSet<>();
    if (dept > 0) {
      for (Category entity : entityList) {
        dtoList.add(entityToDto(entity, dept));
      }
    }
    return dtoList;
  }

  public static String mapLanguage(LanguageCode language) {
    String dtoLanguage = "English";

    if (language != null) {
      switch (language) {
        case en:
          dtoLanguage = "English";
          break;
        case de:
          dtoLanguage = "German";
          break;
      }
    }

    return dtoLanguage;
  }

}
