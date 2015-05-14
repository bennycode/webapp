package com.welovecoding.data.video;

import com.welovecoding.data.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends BaseRepository<Video, Long>/*, PagingAndSortingRepository<Account, Long> */ {

  @Query("SELECT v FROM Video v WHERE v.code = :code")
  Video findByCode(@Param("code") String code);

  @Query("SELECT v FROM Video v WHERE v.id = :videoid AND v.playlist.category.id = :categoryid AND v.playlist.id = :playlistid")
  Video findInCategoryAndPlaylist(@Param("categoryid") Long categoryid, @Param("playlistid") Long playlistid, @Param("videoid") Long videoid);

  @Query("SELECT v FROM Video v WHERE v.id = :videoid AND v.playlist.id = :playlistid")
  Video findInPlaylist(@Param("playlistid") Long playlistid, @Param("videoid") Long videoid);

  @Query("SELECT v FROM Video v WHERE v.playlist.id = :playlistid")
  List<Video> findAllInPlaylist(@Param("playlistid") Long playlistid);

  @Query("SELECT v FROM Video v WHERE v.playlist.category.id = :categoryid AND v.playlist.id = :playlistid")
  List<Video> findAllInCategoryAndPlaylist(@Param("categoryid") Long categoryid, @Param("playlistid") Long playlistid);

  @Query("SELECT v FROM Video v WHERE v.id = :videoid AND v.playlist.category.id = :categoryid")
  Video findInCategory(@Param("categoryid") Long categoryid, @Param("videoid") Long videoid);

  @Query("SELECT v FROM Video v WHERE v.playlist.category.id = :categoryid")
  List<Video> findAllInCategory(@Param("categoryid") Long categoryid);

  @Query("SELECT v FROM Video v WHERE v.slug = :videoslug AND v.playlist.id = :playlistid")
  Video findByPlaylistAndSlug(@Param("playlistid") long playlistid, @Param("videoslug") String slug);
}
