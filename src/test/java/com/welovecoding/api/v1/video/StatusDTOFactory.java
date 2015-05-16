package com.welovecoding.api.v1.video;


import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatusDTOFactory {

  private static final Logger LOG = Logger.getLogger(StatusDTOFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static StatusDTO constructStatusDTO(Integer id, int dept) {
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }
    LOG.log(Level.FINE, "Constructing StatusDTO");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new StatusDTO();
    }
    return null;
  }

}
