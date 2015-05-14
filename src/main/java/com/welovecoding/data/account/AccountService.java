package com.welovecoding.data.account;


import com.welovecoding.data.base.exception.NoEntityToDeleteFoundException;
import com.welovecoding.data.base.exception.NoEntityToUpdateFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.welovecoding.ParameterValidator.*;
import static com.welovecoding.data.base.EntityValidator.validateEntity;

@Service
public class AccountService {

  private final AccountRepository accountRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  private AccountRepository getRepository() {
    return accountRepository;
  }

  public Account findOne(Long id) {
    notNull(id, new IllegalArgumentException("ID is null"));
    return getRepository().findOne(id);
  }

  public Account findOneByUsername(String username) {
    notNull(username, new IllegalArgumentException("Username is null"));
    notEmpty(username, new IllegalArgumentException("Username is empty"));
    return getRepository().findByUsername(username);
  }

  public List<Account> findAll() {
    return getRepository().findAll();
  }

  public Account save(Account entity) {
    notNull(entity, new IllegalArgumentException("Entity is null"));
    isNull(entity.getId(), new IllegalArgumentException("ID of entity is set"));
    validateEntity(entity);
    return getRepository().save(entity);
  }

  public Account update(Account entity) throws NoEntityToUpdateFoundException {
    notNull(entity, new IllegalArgumentException("Entity is null"));
    notNull(entity.getId(), new IllegalArgumentException("ID is null"));
    validateEntity(entity);
    if (!getRepository().exists(entity.getId())) {
      throw new NoEntityToUpdateFoundException("Can't find the entity to update");
    } else {
      return getRepository().save(entity);
    }
  }

  public void delete(Long id) throws NoEntityToDeleteFoundException {
    notNull(id, new IllegalArgumentException("ID is null"));
    if (!getRepository().exists(id)) {
      throw new NoEntityToDeleteFoundException("Can't find the entity to delete");
    } else {
      getRepository().delete(id);
    }
  }
}
