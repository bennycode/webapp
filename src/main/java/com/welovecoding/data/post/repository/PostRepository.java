package com.welovecoding.data.post.repository;

import com.welovecoding.data.base.BaseRepository;
import com.welovecoding.data.base.PagingAndSortingRepository;
import com.welovecoding.data.post.entity.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends BaseRepository<Post, Long>, PagingAndSortingRepository<Post, Long> {

}
