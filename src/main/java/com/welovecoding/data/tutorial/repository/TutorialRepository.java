package com.welovecoding.data.tutorial.repository;

import com.welovecoding.data.base.BaseRepository;
import com.welovecoding.data.base.PagingAndSortingRepository;
import com.welovecoding.data.tutorial.entity.Tutorial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends BaseRepository<Tutorial, Long>, PagingAndSortingRepository<Tutorial, Long> {

    @Query("SELECT p FROM Tutorial p WHERE p.slug = :tutorialslug")
    Tutorial findBySlug(@Param("tutorialslug") String slug);

}
