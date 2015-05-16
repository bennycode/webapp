package com.welovecoding.data.playlist;


import com.welovecoding.data.author.Author;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
      return new Author("authorname" + id);
    }
    return null;
  }


  public static Collection<Author> constructAuthorList(int size, int startId, int dept) {
    LOG.log(Level.FINE, "Constructing Author List");
    Collection<Author> list = new ArrayList<>();
    dept--;
    if (dept > -1) {
      for (int i = 0 + startId; i < (size + startId); i++) {
        list.add(new Author("authorname" + i));
      }
    }
    return list;
  }

}
