package com.welovecoding.data.user;

import com.welovecoding.data.base.BaseRepository;
import com.welovecoding.data.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long>/*, PagingAndSortingRepository<Account, Long> */ {

  @Query("SELECT u FROM User u WHERE u.email = :email")
  User findByEmail(@Param("email") String email);

}
