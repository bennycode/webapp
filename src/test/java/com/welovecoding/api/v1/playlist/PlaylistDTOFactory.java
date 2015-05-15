package com.welovecoding.api.v1.playlist;


import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaylistDTOFactory {

  private static final Logger LOG = Logger.getLogger(PlaylistDTOFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static PlaylistDTO constructPlaylistDTO(Integer id, int dept) {
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
      return new PlaylistDTO(longId, "Playlist", "color", 1, Arrays.asList(new String[]{"lang"}));
    }
    return null;
  }

}
