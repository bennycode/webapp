package com.welovecoding.data.account;

import com.welovecoding.data.news.News;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountFactory {

  private static final Logger LOG = Logger.getLogger(AccountFactory.class.getName());

  public static final Date FIXED_DATE = new Date(1000L);

  static {
    LOG.setLevel(Level.INFO);
  }

  public static Account constructAccount(Integer id, int dept) {
    LOG.log(Level.FINE, "Constructing Account");
    Long longId = null;
    dept--;
    if (dept > -1) {
      if (id != null) {
        longId = new Long(id);
      }
      return new Account(longId, FIXED_DATE, FIXED_DATE, new HashSet<>(com.welovecoding.data.news.NewsFactory.constructNewsList(10, 1, dept)), "username" + id, "password" + id);
    }
    return null;
  }

  public static Account constructAccountWithNullNews(Integer id) {
    LOG.log(Level.FINE, "Constructing Account With Null News");
    Long longId = null;
    if (id != null) {
      longId = new Long(id);
    }
    return new Account(longId, FIXED_DATE, FIXED_DATE, null, "username" + id, "password" + id);
  }

  public static Collection<Account> constructAccountList(int size, int startId, int dept) {
    LOG.log(Level.FINE, "Constructing Account List");
    Collection<Account> newsList = new ArrayList<>();
    dept--;
    if (dept > -1) {
      for (int i = 0 + startId; i < (size + startId); i++) {
        Set<News> news = null;
        if (dept > -1) {
          news = new HashSet<>(com.welovecoding.data.news.NewsFactory.constructNewsList(10, 1, dept));
        }
        newsList.add(new Account(new Long(i), FIXED_DATE, FIXED_DATE, news, "username" + i, "password" + i));
      }
    }
    return newsList;
  }

}
