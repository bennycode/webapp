package com.welovecoding.data.author;

import com.welovecoding.data.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends BaseRepository<Author, Long>/*, PagingAndSortingRepository<Account, Long> */ {


  @Query("SELECT a FROM Author a ORDER BY a.name")
  List<Author> findAllOrderedByName();

}
