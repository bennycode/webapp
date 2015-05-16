package com.welovecoding.api.v1.playlist;


import com.welovecoding.api.v1.category.CategoryDTO;

import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatusDTOFactory {

  private static final Logger LOG = Logger.getLogger(StatusDTOFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static CategoryDTO constructCategoryDTO(Integer id, int dept) {
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
      return new CategoryDTO(longId, "category", "color", 1, Arrays.asList(new String[]{"lang"}));
    }
    return null;
  }

}
