package com.welovecoding.data.account;


import com.welovecoding.data.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseRepository<Account, Long>/*, PagingAndSortingRepository<Account, Long> */ {

  @Query("SELECT a FROM Account a WHERE a.username = :username")
  public Account findByUsername(@Param("username") String username);

}
