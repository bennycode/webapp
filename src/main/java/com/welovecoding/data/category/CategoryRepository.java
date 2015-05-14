package com.welovecoding.data.category;

import com.welovecoding.data.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long>/*, PagingAndSortingRepository<Account, Long> */ {

  @Query("SELECT c FROM Category c ORDER BY c.name")
  List<Category> findAllOrderedByName();

  @Query("SELECT c FROM Category c WHERE c.slug = :categoryslug")
  Category findBySlug(@Param("categoryslug") String slug);

}
