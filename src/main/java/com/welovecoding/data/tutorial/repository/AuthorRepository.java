package com.welovecoding.data.tutorial.repository;

import com.welovecoding.data.base.BaseRepository;
import com.welovecoding.data.base.PagingAndSortingRepository;
import com.welovecoding.data.tutorial.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends BaseRepository<Author, Long>, PagingAndSortingRepository<Author, Long> {

    @Query("SELECT p FROM Author p WHERE p.slug = :authorslug")
    Author findBySlug(@Param("authorslug") String slug);
}
