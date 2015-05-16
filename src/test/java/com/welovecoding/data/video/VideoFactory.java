package com.welovecoding.data.video;


import com.welovecoding.data.news.News;
import com.welovecoding.data.news.NewsFactory;
import com.welovecoding.data.playlist.PlaylistFactory;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VideoFactory {

  private static final Logger LOG = Logger.getLogger(VideoFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static Video constructVideo(Integer id, int dept) {
    LOG.log(Level.FINE, "Constructing Video");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new Video(longId, FIXED_DATE, FIXED_DATE, "code", "description", PlaylistFactory.constructPlaylist(id, dept), "preview/url", "downloadUrl", "perma/link");
    }
    return null;
  }

  public static Video constructVideoWithNullValues(Integer id) {
    LOG.log(Level.FINE, "Constructing Video With Null Values");
    Long longId = null;
    if (id != null) {
      longId = new Long(id);
    }
    return new Video(longId, FIXED_DATE, FIXED_DATE, "code", "description", null, "preview/url", "downloadUrl", "perma/link");
  }

  public static Collection<Video> constructVideoList(int size, int startId, int dept) {
    LOG.log(Level.FINE, "Constructing Video List");
    Collection<Video> newsList = new ArrayList<>();
    dept--;
    if (dept > -1) {
      for (int i = 0 + startId; i < (size + startId); i++) {
        Set<News> news = null;
        if (dept > -1) {
          news = new HashSet<>(NewsFactory.constructNewsList(10, 1, dept));
        }
        newsList.add(new Video(new Long(i), FIXED_DATE, FIXED_DATE, "code", "description", PlaylistFactory.constructPlaylist(i, dept), "preview/url", "downloadUrl", "perma/link"));
      }
    }
    return newsList;
  }

}
