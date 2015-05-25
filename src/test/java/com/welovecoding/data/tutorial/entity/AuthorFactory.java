package com.welovecoding.data.tutorial.entity;


import com.welovecoding.data.user.entity.UserFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorFactory {

  private static final Logger LOG = Logger.getLogger(AuthorFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static Author constructOne(Integer id, int dept) {
    LOG.log(Level.FINE, "Constructing Author");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new Author(
        longId,
        FIXED_DATE,
        FIXED_DATE,
        "slug1",
        "firstname1",
        "lastname1",
        "desc1",
        "website1",
        "channelUrl1",
        UserFactory.constructOne(1, dept));
    }
    return null;
  }


  public static List<Author> constructList(int size, int startId, int dept) {
    LOG.log(Level.FINE, "Constructing Author List");
    List<Author> list = new ArrayList<>();
    dept--;
    if (dept > -1) {
      for (int i = 0 + startId; i < (size + startId); i++) {
        list.add(new Author(
          new Long(i),
          FIXED_DATE,
          FIXED_DATE,
          "slug" + i,
          "firstname" + i,
          "lastname" + i,
          "desc" + i,
          "website" + i,
          "channelUrl" + i,
          UserFactory.constructOne(i, dept)));
      }
    }
    return list;
  }

}
