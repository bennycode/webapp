package com.welovecoding.api.v1.playlist;


import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorDTOFactory {

  private static final Logger LOG = Logger.getLogger(AuthorDTOFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static AuthorDTO constructAuthorDTO(Integer id, int dept) {
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }
    LOG.log(Level.FINE, "Constructing AuthorDTO");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new AuthorDTO(longId, "Author", "color", 1, Arrays.asList(new String[]{"lang"}));
    }
    return null;
  }

}
