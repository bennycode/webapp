package com.welovecoding.data.news;


import com.welovecoding.data.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends BaseRepository<News, Long>/*, PagingAndSortingRepository<News, Long> */ {

  @Query("SELECT n FROM News n WHERE n.slug = :slug")
  public News findOneBySlug(@Param("slug") String slug);

}
