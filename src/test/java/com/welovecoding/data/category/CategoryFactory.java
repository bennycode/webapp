package com.welovecoding.data.category;


import com.welovecoding.data.playlist.PlaylistFactory;
import com.welovecoding.data.playlist.Playlist;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryFactory {

  private static final Logger LOG = Logger.getLogger(CategoryFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }


  // http://www.welovecoding.com/rest/service/v1/categories
  public static Category constructCategory(Integer id, int dept) {
    LOG.log(Level.FINE, "Constructing Category");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new Category(longId, FIXED_DATE, FIXED_DATE, "#EF9608", new HashSet<>(PlaylistFactory.constructPlaylistList(10, 1, dept)));
    }
    return null;
  }

  public static Collection<Category> constructCategoryList(int size, int startId, int dept) {
    LOG.log(Level.FINE, "Constructing Category List");
    Collection<Category> categories = new ArrayList<>();
    dept--;
    if (dept > -1) {
      for (int i = 0 + startId; i < (size + startId); i++) {
        Set<Playlist> playlists = null;
        if (dept > -1) {
          playlists = new HashSet<>(PlaylistFactory.constructPlaylistList(10, 1, dept));
        }
        categories.add(new Category(new Long(i), FIXED_DATE, FIXED_DATE, "#EF9608", playlists));
      }
    }
    return categories;
  }

}
