package com.welovecoding.api.v1.video;


import com.welovecoding.api.v1.playlist.PlaylistDTOFactory;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VideoDTOFactory {

  private static final Logger LOG = Logger.getLogger(VideoDTOFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static VideoDTO constructVideoDTO(Integer id, int dept) {
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }
    LOG.log(Level.FINE, "Constructing VideoDTO");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new VideoDTO(longId, "videoName", "description", "code", "prevUrl", "downloadUrl", "permaLink", PlaylistDTOFactory.constructPlaylistDTO(1, 1));
    }
    return null;
  }

}
