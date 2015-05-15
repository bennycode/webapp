package com.welovecoding.api.v1.playlist;


import com.welovecoding.api.v1.video.StatusDTO;
import com.welovecoding.api.v1.video.VideoDTO;
import com.welovecoding.api.v1.video.VideoMapper;
import com.welovecoding.data.author.Author;
import com.welovecoding.data.playlist.entity.LanguageCode;
import com.welovecoding.data.playlist.entity.Playlist;

import java.util.HashSet;
import java.util.Set;

public class PlaylistMapper {

  public static PlaylistDTO entityToDto(Playlist entity, int dept) {
    if (entity == null || dept == 0) {
      return null;
    }
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }
    Set<VideoDTO> entitySetToDtoSet = new HashSet<>();
    dept--;
    if (entity.getVideos() != null) {
      entitySetToDtoSet = VideoMapper.entitySetToDtoSet(entity.getVideos(), dept);
    }

    PlaylistDTO dto = new PlaylistDTO();

    dto.setId(entity.getId());
    dto.setName(entity.getName());
//    dto.setLanguage(mapLanguage(entity.getLanguageCode()));
    dto.setCategoryName(entity.getCategory().getName());
    dto.setProviderName(entity.getProvider().toString());
    dto.setNumberOfVideos(entity.getVideos().size());
    dto.setDescription(entity.getDescription());
    dto.setOwner(mapAuthor(entity.getAuthor()));
    dto.setStatus(new StatusDTO());
//    if (playlist.getDifficulty() != null) {
//      dtoPlaylist.setDifficulty(String.valueOf(playlist.getDifficulty()));
//    }
    return dto;
  }

  public static Set<PlaylistDTO> entitySetToDtoSet(Set<Playlist> entityList, int dept) {
    Set<PlaylistDTO> dtoList = new HashSet<>();
    if (dept > 0) {
      for (Playlist playlist : entityList) {
        dtoList.add(entityToDto(playlist, dept));
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

  public static AuthorDTO mapAuthor(Author entity) {
    AuthorDTO dto = new AuthorDTO();

    dto.setDescription(entity.getDescription());
    dto.setName(entity.getName());
    dto.setWebsite(entity.getWebsite());

    return dto;
  }

}
