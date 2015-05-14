package com.welovecoding.api.v1.news;


import com.welovecoding.api.v1.account.AccountResource;
import com.welovecoding.api.v1.base.Logged;
import com.welovecoding.data.base.exception.NoEntityToDeleteFoundException;
import com.welovecoding.data.base.exception.NoEntityToUpdateFoundException;
import com.welovecoding.data.news.News;
import com.welovecoding.data.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.welovecoding.api.v1.news.NewsMapper.entityToDto;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/v1/news", produces = "application/hal+json")
@Logged
public class NewsResource {

  private final NewsService newsService;

  @Autowired
  NewsResource(NewsService newsService) {
    this.newsService = newsService;
  }

  @RequestMapping(value = "/{newsSlug}", method = RequestMethod.GET)
  public NewsDTO findOneNews(@PathVariable String newsSlug) {
    NewsDTO dto = entityToDto(newsService.findOneBySlug(newsSlug), 2);
    dto.add(linkTo(ControllerLinkBuilder.methodOn(NewsResource.class).findOneNews(newsSlug)).withSelfRel());
    if (dto.getAccount() != null) {
      dto.add(linkTo(ControllerLinkBuilder.methodOn(AccountResource.class).findAccountByUsername(dto.getAccount().getUsername())).withRel("account"));
    }
    return dto;
  }

  @RequestMapping(method = RequestMethod.GET)
  public Resources<NewsDTO> getAllNews() {
    List<Link> parentLinks = new ArrayList<>();
    parentLinks.add(linkTo(ControllerLinkBuilder.methodOn(NewsResource.class).getAllNews()).withSelfRel());

    Collection<NewsDTO> dtoList = NewsMapper.entityListToDtoList(newsService.findAll(), 2);
    for (NewsDTO dto : dtoList) {
      dto.add(linkTo(ControllerLinkBuilder.methodOn(NewsResource.class).findOneNews(dto.getSlug())).withSelfRel());
      if (dto.getAccount() != null) {
        dto.add(linkTo(ControllerLinkBuilder.methodOn(AccountResource.class).findAccountByUsername(dto.getAccount().getUsername())).withRel("account"));
      }
      parentLinks.add(linkTo(ControllerLinkBuilder.methodOn(NewsResource.class).findOneNews(dto.getSlug())).withRel("item"));
    }
    return new Resources(dtoList, parentLinks);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
  News addNews(@RequestBody NewsDTO newsDto) {
    System.out.println(newsDto);
    return newsService.save(NewsMapper.dtoToEntity(newsDto));
  }

  @RequestMapping(value = "/{newsSlug}", method = RequestMethod.PUT)
  News updateNews(@PathVariable String newsSlug, NewsDTO news) throws NoEntityToUpdateFoundException {
    if (!newsSlug.equalsIgnoreCase(news.getSlug())) {
      throw new NoEntityToUpdateFoundException("Parameters and values of JSON are different.");
    }
    return newsService.update(NewsMapper.dtoToEntity(news));
  }

  @RequestMapping(value = "/{newsSlug}", method = RequestMethod.DELETE)
  void deleteNews(@PathVariable String newsSlug) throws NoEntityToDeleteFoundException {
    newsService.deleteBySlug(newsSlug);
  }

}
