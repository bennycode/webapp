package com.welovecoding.data.playlist;


import com.welovecoding.data.author.Author;
import com.welovecoding.data.news.News;
import com.welovecoding.data.news.NewsFactory;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorFactory {

  private static final Logger LOG = Logger.getLogger(AuthorFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static Author constructAuthor(Integer id, int dept) {
    LOG.log(Level.FINE, "Constructing Author");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new Author(longId, FIXED_DATE, FIXED_DATE, new HashSet<>(NewsFactory.constructNewsList(10, 1, dept)), "username" + id, "password" + id);
    }
    return null;
  }

  public static Author constructAuthorWithNullNews(Integer id) {
    LOG.log(Level.FINE, "Constructing Author With Null News");
    Long longId = null;
    if (id != null) {
      longId = new Long(id);
    }
    return new Author(longId, FIXED_DATE, FIXED_DATE, null, "username" + id, "password" + id);
  }

  public static Collection<Author> constructAuthorList(int size, int startId, int dept) {
    LOG.log(Level.FINE, "Constructing Author List");
    Collection<Author> newsList = new ArrayList<>();
    dept--;
    if (dept > -1) {
      for (int i = 0 + startId; i < (size + startId); i++) {
        Set<News> news = null;
        if (dept > -1) {
          news = new HashSet<>(NewsFactory.constructNewsList(10, 1, dept));
        }
        newsList.add(new Author(new Long(i), FIXED_DATE, FIXED_DATE, news, "username" + i, "password" + i));
      }
    }
    return newsList;
  }

}
