package com.welovecoding.api.v1.account;


import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDTOFactory {

  private static final Logger LOG = Logger.getLogger(AccountDTOFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static AccountDTO constructAccountDTO(Integer id, int dept) {
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }
    LOG.log(Level.FINE, "Constructing AccountDTO");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new AccountDTO(longId, FIXED_DATE, FIXED_DATE, new HashSet<>(com.welovecoding.api.v1.news.NewsDTOFactory.constructNewsDTOSet(10, 1, dept)), "username" + id);
    }
    return null;
  }

  public static AccountDTO constructAccountDTOWithNullNews(Integer id) {
    LOG.log(Level.FINE, "Constructing AccountDTO with null News");
    Long longId = null;
    if (id != null) {
      longId = new Long(id);
    }
    return new AccountDTO(longId, FIXED_DATE, FIXED_DATE, null, "username" + id);
  }
}
