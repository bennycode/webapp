package com.welovecoding.data.playlist;

import com.welovecoding.data.base.BaseRepository;
import com.welovecoding.data.playlist.entity.Playlist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends BaseRepository<Playlist, Long>/*, PagingAndSortingRepository<Account, Long> */ {

  @Query("SELECT p FROM Playlist p WHERE p.code = :code")
  Playlist findByCode(@Param("code") String code);

  @Query("SELECT p FROM Playlist p WHERE p.id = :playlistid AND p.category.id = :categoryid")
  Playlist findInCategory(@Param("categoryid") Long categoryid, @Param("playlistid") Long playlistid);

  @Query("SELECT p FROM Playlist p WHERE p.slug = :playlistslug AND p.category.id = :categoryid")
  Playlist findByCategoryAndSlug(@Param("categoryid") Long categoryid, @Param("playlistslug") String slug);

  @Query("SELECT p FROM Playlist p WHERE p.category.id = :categoryid")
  List<Playlist> findAllInCategory(@Param("categoryid") Long categoryid);

}
