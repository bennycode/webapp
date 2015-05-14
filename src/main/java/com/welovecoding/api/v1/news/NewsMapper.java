package com.welovecoding.api.v1.news;


import com.welovecoding.api.v1.account.AccountDTO;
import com.welovecoding.api.v1.account.AccountMapper;
import com.welovecoding.data.account.Account;
import com.welovecoding.data.news.News;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewsMapper {

  public static NewsDTO entityToDto(News entity, int dept) {
    if (entity == null || dept == 0) {
      return null;
    }
    if (dept < 0) {
      throw new IllegalArgumentException("Depts smaller than 0 are not allowed!");
    }
    AccountDTO entityToDto = null;
    dept--;
    if (entity.getAccount() != null) {
      entityToDto = AccountMapper.entityToDto(entity.getAccount(), dept);
    }
    NewsDTO dto = new NewsDTO(
      entity.getId(),
      entity.getCreated(),
      entity.getLastModified(),
      entityToDto,
      entity.getSlug(),
      entity.getTitle(),
      entity.getDescription(),
      entity.getText());
    return dto;
  }

  public static News dtoToEntity(NewsDTO dto) {
    if (dto == null) {
      return null;
    }
    Account account = null;
    if (dto.getAccount() != null) {
//	    account = AccountMapper.dtoToEntity(dto.getAccount());
    }
    News entity = new News(
      account,
      dto.getSlug(),
      dto.getTitle(),
      dto.getDescription(),
      dto.getText());
    return entity;
  }

  public static List<NewsDTO> entityListToDtoList(List<News> entityList, int dept) {
    List<NewsDTO> dtoList = new ArrayList<>();
    if (dept > 0) {
      for (News news : entityList) {
        dtoList.add(entityToDto(news, dept));
      }
    }
    return dtoList;
  }

  public static Set<NewsDTO> entitySetToDtoSet(Set<News> entityList, int dept) {
    Set<NewsDTO> dtoList = new HashSet<>();
    if (dept > 0) {
      for (News news : entityList) {
        dtoList.add(entityToDto(news, dept));
      }
    }
    return dtoList;
  }
}
