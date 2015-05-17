package com.welovecoding.data.playlist;


import com.welovecoding.data.category.CategoryFactory;
import com.welovecoding.data.video.Video;
import com.welovecoding.data.video.VideoFactory;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaylistFactory {

  private static final Logger LOG = Logger.getLogger(PlaylistFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  // http://www.welovecoding.com/rest/service/v1/category/8
  public static Playlist constructPlaylist(Integer id, int dept) {
    LOG.log(Level.FINE, "Constructing Playlist");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new Playlist(longId,
        "playlistName",
        "playlistSlug",
        FIXED_DATE,
        FIXED_DATE,
        null,
        null,
        "YOUTUBE",
        CategoryFactory.constructCategory(1, 1),
        AuthorFactory.constructAuthor(1, 1),
        new HashSet<>(VideoFactory.constructVideoList(10, 1, 1)),
        "code",
        "description",
        true
      );
    }
    return null;
  }

  public static Playlist constructPlaylistWithNullValues(Integer id) {
    LOG.log(Level.FINE, "Constructing Playlist With Null Values");
    Long longId = null;
    if (id != null) {
      longId = new Long(id);
    }
    return new Playlist(longId,
      "playlistName",
      "playlistSlug",
      FIXED_DATE,
      FIXED_DATE,
      null,
      null,
      null,
      CategoryFactory.constructCategory(1, 1),
      null,
      null,
      "code",
      "description",
      true
    );
  }

  public static Collection<Playlist> constructPlaylistList(int size, int startId, int dept) {
    LOG.log(Level.FINE, "Constructing Playlist List");
    Collection<Playlist> playlists = new ArrayList<>();
    dept--;
    if (dept > -1) {
      for (int i = 0 + startId; i < (size + startId); i++) {
        Set<Video> videos = null;
        if (dept > -1) {
          videos = new HashSet<>(VideoFactory.constructVideoList(10, 1, dept));
        }
        playlists.add(new Playlist(new Long(i),
          "playlistName",
          "playlistSlug",
          FIXED_DATE,
          FIXED_DATE,
          null,
          null,
          "YOUTUBE",
          CategoryFactory.constructCategory(1, 1),
          AuthorFactory.constructAuthor(1, 1),
          new HashSet<>(VideoFactory.constructVideoList(10, 1, 1)),
          "code",
          "description",
          true
        ));
      }
    }
    return playlists;
  }

}
