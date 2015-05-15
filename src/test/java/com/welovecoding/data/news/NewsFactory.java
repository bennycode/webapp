package com.welovecoding.data.news;

import com.welovecoding.data.account.Account;
import com.welovecoding.data.account.AccountFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewsFactory {

  private static final Logger LOG = Logger.getLogger(NewsFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static News constructNews(Integer id, int dept) {
    LOG.log(Level.FINE, "Constructing News");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new News(longId, FIXED_DATE, FIXED_DATE, AccountFactory.constructAccount(id, dept)/*, "slug" + i*/, "title" + id, "description" + id, "text" + id);
    }
    return null;
  }

  public static Collection<News> constructNewsList(int size, int startId, int dept) {
    Collection<News> newsList = new ArrayList<>();
    dept--;
    if (dept > -1) {
      for (int i = 0 + startId; i < (size + startId); i++) {
        Account account = null;
        if (dept > -1) {
          account = AccountFactory.constructAccount(i, dept);
        }
        newsList.add(new News((long) i, FIXED_DATE, FIXED_DATE, account/*, "slug" + i*/, "title" + i, "description" + i, "text" + i));
      }
    }
    return newsList;
  }

  public static Collection<News> constructNewsListWithAccount(int size, int startId, Account account) {
    Collection<News> newsList = new ArrayList<>();
    for (int i = 0 + startId; i < (size + startId); i++) {
      newsList.add(new News((long) i, FIXED_DATE, FIXED_DATE, account/*, "slug" + i*/, "title" + i, "description" + i, "text" + i));
    }
    return newsList;
  }
}
