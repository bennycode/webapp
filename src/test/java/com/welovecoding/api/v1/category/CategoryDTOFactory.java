package com.welovecoding.api.v1.category;


import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDTOFactory {

  private static final Logger LOG = Logger.getLogger(CategoryDTOFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static CategoryDTO constructOne(Integer id, int numberOfVideos, int dept) {
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }
    LOG.log(Level.FINE, "Constructing CategoryDTO");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new CategoryDTO(
        longId,
        FIXED_DATE,
        FIXED_DATE,
        "slug1",
        "title1",
        "#EF9608",
        null);
    }
    return null;
  }

}
