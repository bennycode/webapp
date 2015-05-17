package com.welovecoding.api.v1.video;


import com.welovecoding.data.category.Category;
import com.welovecoding.data.playlist.Playlist;
import com.welovecoding.data.video.Video;

import java.util.HashSet;
import java.util.Set;

public class VideoMapper {

  public static VideoDTO entityToDto(Video entity, int dept) {
    if (entity == null || dept == 0) {
      return null;
    }
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }

    VideoDTO dto = new VideoDTO();

    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setDescription(entity.getDescription());
    dto.setCode(entity.getCode());
    dto.setDownloadUrl(entity.getDownloadUrl());
    Playlist playlist = entity.getPlaylist();
    if (playlist != null) {
      Category category = playlist.getCategory();

      if (playlist.getProvider().equals("YOUTUBE")) {
        dto.setPreviewImageUrl(String.format("http://img.youtube.com/vi/%s/1.jpg", entity.getCode()));
      }
      if (category != null) {
        String permalink = String.format("http://www.welovecoding.com/tutorials/%s/%s?video=%s", category.getSlug(), playlist.getSlug(), entity.getId());
        dto.setPermalink(permalink);
      }
    }


    return dto;
  }

  public static Set<VideoDTO> entitySetToDtoSet(Set<Video> entityList, int dept) {
    Set<VideoDTO> dtoList = new HashSet<>();
    if (dept > 0) {
      for (Video entity : entityList) {
        dtoList.add(entityToDto(entity, dept));
      }
    }
    return dtoList;
  }

}
