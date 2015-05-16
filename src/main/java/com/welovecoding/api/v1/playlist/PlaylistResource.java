package com.welovecoding.api.v1.playlist;


import com.welovecoding.data.playlist.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.Set;

import static com.welovecoding.api.v1.playlist.PlaylistMapper.entitySetToDtoSet;


@RestController
@RequestMapping("/api/v1/category")
@Produces("application/json")
public class PlaylistResource {

  private final PlaylistService playlistService;

  @Autowired
  PlaylistResource(PlaylistService playlistService) {
    this.playlistService = playlistService;
  }


  @RequestMapping(value = "/{categoryid}", method = RequestMethod.GET)
  public Set<PlaylistDTO> findAllInCategory(@PathVariable Long categoryid) {
    return entitySetToDtoSet(playlistService.findAllInCategory(categoryid), 2);
  }
}
