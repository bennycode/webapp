package com.welovecoding.data.playlist;


import com.welovecoding.data.news.News;
import com.welovecoding.data.news.NewsFactory;
import com.welovecoding.data.playlist.entity.Playlist;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaylistFactory {

  private static final Logger LOG = Logger.getLogger(PlaylistFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static Playlist constructPlaylist(Integer id, int dept) {
    LOG.log(Level.FINE, "Constructing Playlist");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new Playlist(longId, FIXED_DATE, FIXED_DATE, new HashSet<>(NewsFactory.constructNewsList(10, 1, dept)), "username" + id, "password" + id);
    }
    return null;
  }

  public static Playlist constructPlaylistWithNullNews(Integer id) {
    LOG.log(Level.FINE, "Constructing Playlist With Null News");
    Long longId = null;
    if (id != null) {
      longId = new Long(id);
    }
    return new Playlist(longId, FIXED_DATE, FIXED_DATE, null, "username" + id, "password" + id);
  }

  public static Collection<Playlist> constructPlaylistList(int size, int startId, int dept) {
    LOG.log(Level.FINE, "Constructing Playlist List");
    Collection<Playlist> newsList = new ArrayList<>();
    dept--;
    if (dept > -1) {
      for (int i = 0 + startId; i < (size + startId); i++) {
        Set<News> news = null;
        if (dept > -1) {
          news = new HashSet<>(NewsFactory.constructNewsList(10, 1, dept));
        }
        newsList.add(new Playlist(new Long(i), FIXED_DATE, FIXED_DATE, news, "username" + i, "password" + i));
      }
    }
    return newsList;
  }

}
