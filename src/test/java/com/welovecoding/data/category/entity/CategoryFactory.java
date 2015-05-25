package com.welovecoding.data.category.entity;


import com.welovecoding.data.tutorial.entity.Tutorial;
import com.welovecoding.data.tutorial.entity.TutorialFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryFactory {

  private static final Logger LOG = Logger.getLogger(CategoryFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static Category constructOne(Integer id, int dept) {
    LOG.log(Level.FINE, "Constructing Category");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new Category(longId, FIXED_DATE, FIXED_DATE, "slug1", "title1", "#EF9608", TutorialFactory.constructList(10, 1, dept));
    }
    return null;
  }

  public static Category constructOneWithNullValues(Integer id) {
    LOG.log(Level.FINE, "Constructing Video With Null Values");
    Long longId = null;
    if (id != null) {
      longId = new Long(id);
    }
    return new Category(longId, FIXED_DATE, FIXED_DATE, "slug1", "title1", "#EF9608", null);
  }

  public static List<Category> constructList(int size, int startId, int dept) {
    LOG.log(Level.FINE, "Constructing Category List");
    List<Category> categories = new ArrayList<>();
    dept--;
    if (dept > -1) {
      for (int i = 0 + startId; i < (size + startId); i++) {
        List<Tutorial> list = new ArrayList<>();
        if (dept > -1) {
          list = TutorialFactory.constructList(10, i, dept);
        }
        categories.add(new Category(new Long(i), FIXED_DATE, FIXED_DATE, "slug" + i, "title" + i, "#EF9608", list));
      }
    }
    return categories;
  }

}
