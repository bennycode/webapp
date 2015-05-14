package com.welovecoding.api.v1.account;


import com.welovecoding.api.v1.news.NewsDTO;
import com.welovecoding.api.v1.news.NewsMapper;
import com.welovecoding.data.account.Account;

import java.util.HashSet;
import java.util.Set;

public class AccountMapper {

  public static AccountDTO entityToDto(Account entity, int dept) {
    if (entity == null || dept == 0) {
      return null;
    }
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }
    Set<NewsDTO> entitySetToDtoSet = new HashSet<>();
    dept--;
    if (entity.getNews() != null) {
      entitySetToDtoSet = NewsMapper.entitySetToDtoSet(entity.getNews(), dept);
    }

    AccountDTO dto = new AccountDTO(
      entity.getId(),
      entity.getCreated(),
      entity.getLastModified(),
      entitySetToDtoSet,
      entity.getUsername());
    return dto;
  }

}
