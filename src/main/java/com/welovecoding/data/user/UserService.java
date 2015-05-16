package com.welovecoding.data.user;

import com.welovecoding.data.base.exception.NoEntityToDeleteFoundException;
import com.welovecoding.data.base.exception.NoEntityToUpdateFoundException;
import com.welovecoding.data.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.welovecoding.ParameterValidator.isNull;
import static com.welovecoding.ParameterValidator.notNull;
import static com.welovecoding.data.base.EntityValidator.validateEntity;

@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  private UserRepository getRepository() {
    return userRepository;
  }

  public User findOne(Long id) {
    notNull(id, new IllegalArgumentException("ID is null"));
    return getRepository().findOne(id);
  }

  public List<User> findAll() {
    return getRepository().findAll();
  }

  public User save(User entity) {
    notNull(entity, new IllegalArgumentException("Entity is null"));
    isNull(entity.getId(), new IllegalArgumentException("ID of entity is set"));
    validateEntity(entity);
    return getRepository().save(entity);
  }

  public User update(User entity) throws NoEntityToUpdateFoundException {
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

  public User findByEmail(String email) {
    return getRepository().findByEmail(email);
  }


}
