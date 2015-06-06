package com.welovecoding.data.user.entity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserFactory {

  private static final Logger LOG = Logger.getLogger(UserFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static User constructOne(Integer id, int dept) {
    LOG.log(Level.FINE, "Constructing Author");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new User(
        longId,
        FIXED_DATE,
        FIXED_DATE,
        "slug1",
        "username1",
        "mail1@mail.com",
        "firstname1",
        "lastname1",
        "password1"
      );
    }
    return null;
  }


  public static Collection<User> constructList(int size, int startId, int dept) {
    LOG.log(Level.FINE, "Constructing Author List");
    Collection<User> list = new ArrayList<>();
    dept--;
    if (dept > -1) {
      for (int i = 0 + startId; i < (size + startId); i++) {
        list.add(new User(
          new Long(i),
          FIXED_DATE,
          FIXED_DATE,
          "slug" + i,
          "username" + i,
          "mail" + i + "@mail.com",
          "firstname" + i,
          "lastname" + i,
          "password" + i
        ));
      }
    }
    return list;
  }

}
