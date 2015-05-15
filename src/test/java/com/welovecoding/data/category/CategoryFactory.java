package com.welovecoding.data.category;


import com.welovecoding.data.news.News;
import com.welovecoding.data.news.NewsFactory;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryFactory {

  private static final Logger LOG = Logger.getLogger(CategoryFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static Category constructCategory(Integer id, int dept) {
    LOG.log(Level.FINE, "Constructing Category");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new Category(longId, FIXED_DATE, FIXED_DATE, new HashSet<>(NewsFactory.constructNewsList(10, 1, dept)), "username" + id, "password" + id);
    }
    return null;
  }

  public static Category constructCategoryWithNullNews(Integer id) {
    LOG.log(Level.FINE, "Constructing Category With Null News");
    Long longId = null;
    if (id != null) {
      longId = new Long(id);
    }
    return new Category(longId, FIXED_DATE, FIXED_DATE, null, "username" + id, "password" + id);
  }

  public static Collection<Category> constructCategoryList(int size, int startId, int dept) {
    LOG.log(Level.FINE, "Constructing Category List");
    Collection<Category> newsList = new ArrayList<>();
    dept--;
    if (dept > -1) {
      for (int i = 0 + startId; i < (size + startId); i++) {
        Set<News> news = null;
        if (dept > -1) {
          news = new HashSet<>(NewsFactory.constructNewsList(10, 1, dept));
        }
        newsList.add(new Category(new Long(i), FIXED_DATE, FIXED_DATE, news, "username" + i, "password" + i));
      }
    }
    return newsList;
  }

}
