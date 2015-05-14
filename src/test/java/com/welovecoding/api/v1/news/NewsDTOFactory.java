package com.welovecoding.api.v1.news;

import com.welovecoding.api.v1.account.AccountDTO;
import com.welovecoding.api.v1.account.AccountDTOFactory;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewsDTOFactory {

  private static final Logger LOG = Logger.getLogger(NewsDTOFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static NewsDTO constructNewsDTO(Integer id, int dept) {
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }
    LOG.log(Level.FINE, "Constructing NewsDTO");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new NewsDTO(longId, FIXED_DATE, FIXED_DATE, AccountDTOFactory.constructAccountDTO(1, dept), "slug" + id, "title" + id, "description" + id, "text" + id);
    }
    return null;
  }

  public static Collection<NewsDTO> constructNewsDTOList(int size, int startId, int dept) {
    Collection<NewsDTO> dtoList = new ArrayList<>();
    dept--;
    if (dept > -1) {
      for (int i = 0 + startId; i < (size + startId); i++) {
        AccountDTO account = null;
        if (dept > 0) {
          account = AccountDTOFactory.constructAccountDTO(i, dept);
        }
        dtoList.add(new NewsDTO((long) i, FIXED_DATE, FIXED_DATE, account, "slug" + i, "title" + i, "description" + i, "text" + i));
      }
    }

    return dtoList;
  }

  public static Set<NewsDTO> constructNewsDTOSet(int size, int startId, int dept) {
    LOG.log(Level.FINE, "Constructing NewsDTO Set");
    Set<NewsDTO> dtoList = new HashSet<>();
    dept--;
    if (dept > -1) {
      for (int i = 0 + startId; i < (size + startId); i++) {
        AccountDTO account = null;
        if (dept > -1) {
          account = AccountDTOFactory.constructAccountDTO(i, dept);
        }
        dtoList.add(new NewsDTO((long) i, FIXED_DATE, FIXED_DATE, account, "slug" + i, "title" + i, "description" + i, "text" + i));
      }
    }
    return dtoList;
  }
}
