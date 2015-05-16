package com.welovecoding.data.playlist;


import com.welovecoding.data.author.Author;
import com.welovecoding.data.playlist.entity.Provider;
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
        FIXED_DATE,
        FIXED_DATE,
        Provider.YOUTUBE,
        null,
        new Author("John Doe"),
        new HashSet<Video>(),
        null,
        "...",
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
      FIXED_DATE,
      FIXED_DATE,
      Provider.YOUTUBE,
      null,
      new Author("John Doe"),
      new HashSet<Video>(),
      null,
      "...",
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
        playlists.add(new Playlist(
          new Long(i),
          FIXED_DATE,
          FIXED_DATE,
          Provider.YOUTUBE,
          null,
          new Author("John Doe"),
          new HashSet<Video>(Arrays.asList(new Video(), new Video())),
          null,
          "...",
          true));
      }
    }
    return playlists;
  }

}
