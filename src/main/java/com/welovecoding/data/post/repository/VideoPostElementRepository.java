package com.welovecoding.data.post.repository;

import com.welovecoding.data.base.BaseRepository;
import com.welovecoding.data.base.PagingAndSortingRepository;
import com.welovecoding.data.post.entity.VideoPostElement;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoPostElementRepository extends BaseRepository<VideoPostElement, Long>,
        PagingAndSortingRepository<VideoPostElement, Long> {

}
