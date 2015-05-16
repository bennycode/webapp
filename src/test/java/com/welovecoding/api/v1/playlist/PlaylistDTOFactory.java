package com.welovecoding.api.v1.playlist;


import com.welovecoding.api.v1.video.StatusDTOFactory;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaylistDTOFactory {

  private static final Logger LOG = Logger.getLogger(PlaylistDTOFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static PlaylistDTO constructPlaylistDTO(Integer id, int numberOfVideos, int dept) {
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }
    LOG.log(Level.FINE, "Constructing PlaylistDTO");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new PlaylistDTO(longId, "playlistName", null, "categoryName", "providerName", numberOfVideos, "description", AuthorDTOFactory.constructAuthorDTO(1, 1), StatusDTOFactory.constructStatusDTO(1, 1));
    }
    return null;
  }

}
