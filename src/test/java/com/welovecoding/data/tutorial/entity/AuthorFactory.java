//package com.welovecoding.data.tutorial.entity;
//
//
//import com.welovecoding.data.user.entity.UserFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class AuthorFactory {
//
//  private static final Logger LOG = LoggerFactory.getLogger(AuthorFactory.class.getName());
//
//  public static final Date FIXED_DATE = new Date(1000L);
//
//  public static Author constructOne(Integer id, int dept) {
//    LOG.info("Constructing Author");
//    Long longId = null;
//    dept--;
//    if (dept > -1) {
//      if (id != null) {
//        longId = new Long(id);
//      }
//      return new Author(
//        longId,
//        FIXED_DATE,
//        FIXED_DATE,
//        "slug1",
//        "firstname1",
//        "lastname1",
//        "desc1",
//        "website1",
//        "channelUrl1",
//        UserFactory.constructOne(1, dept));
//    }
//    return null;
//  }
//
//
//  public static List<Author> constructList(int size, int startId, int dept) {
//    LOG.info("Constructing Author List");
//    List<Author> list = new ArrayList<>();
//    dept--;
//    if (dept > -1) {
//      for (int i = startId; i < (size + startId); i++) {
//        list.add(new Author(
//          new Long(i),
//          FIXED_DATE,
//          FIXED_DATE,
//          "slug" + i,
//          "firstname" + i,
//          "lastname" + i,
//          "desc" + i,
//          "website" + i,
//          "channelUrl" + i,
//          UserFactory.constructOne(i, dept)));
//      }
//    }
//    return list;
//  }
//
//}
