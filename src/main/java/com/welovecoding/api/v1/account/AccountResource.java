package com.welovecoding.api.v1.account;


import com.welovecoding.api.v1.news.NewsDTO;
import com.welovecoding.api.v1.news.NewsResource;
import com.welovecoding.data.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.welovecoding.api.v1.account.AccountMapper.entityToDto;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/account")
public class AccountResource {

  private final AccountService accountService;

  @Autowired
  AccountResource(AccountService accountService) {
    this.accountService = accountService;
  }

  @RequestMapping(value = "/{accountUsername}", method = RequestMethod.GET)
  public AccountDTO findAccountByUsername(@PathVariable String accountUsername) {
    AccountDTO dto = entityToDto(accountService.findOneByUsername(accountUsername), 2);
    dto.add(linkTo(ControllerLinkBuilder.methodOn(AccountResource.class).findAccountByUsername(accountUsername)).withSelfRel());

    for (NewsDTO news : dto.getNews()) {
      dto.add(linkTo(ControllerLinkBuilder.methodOn(NewsResource.class).findOneNews(news.getSlug())).withRel("news"));
    }

    return dto;
  }
}
