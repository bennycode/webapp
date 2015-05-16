package com.welovecoding.api.v1.video;


import com.welovecoding.data.video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.Set;

import static com.welovecoding.api.v1.video.VideoMapper.entitySetToDtoSet;


@RestController
@RequestMapping("/api/v1/category/{categoryid}/playlist")
@Produces("application/json")
public class VideoResource {

  private final VideoService videoService;

  @Autowired
  VideoResource(VideoService videoService) {
    this.videoService = videoService;
  }


  @RequestMapping(value = "/{playlistid}", method = RequestMethod.GET)
  public Set<VideoDTO> findAllInCategoryAndPlaylist(@PathVariable Long categoryid, @PathVariable Long playlistid) {
    return entitySetToDtoSet(videoService.findAllInCategoryAndPlaylist(categoryid, playlistid), 2);
  }
}
