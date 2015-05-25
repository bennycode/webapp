package com.welovecoding.data.category.repository;

import com.welovecoding.data.base.BaseRepository;
import com.welovecoding.data.base.PagingAndSortingRepository;
import com.welovecoding.data.category.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long>, PagingAndSortingRepository<Category, Long> {

  @Query("SELECT c FROM Category c WHERE c.slug = :categoryslug")
  Category findBySlug(@Param("categoryslug") String slug);

}
