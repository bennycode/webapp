package com.welovecoding.data.video;

import com.welovecoding.data.base.exception.NoEntityToDeleteFoundException;
import com.welovecoding.data.base.exception.NoEntityToUpdateFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.welovecoding.ParameterValidator.isNull;
import static com.welovecoding.ParameterValidator.notNull;
import static com.welovecoding.data.base.EntityValidator.validateEntity;

@Service
public class VideoService {

  private final VideoRepository videoRepository;

  @Autowired
  public VideoService(VideoRepository videoRepository) {
    this.videoRepository = videoRepository;
  }

  private VideoRepository getRepository() {
    return videoRepository;
  }

  public Video findOne(Long id) {
    notNull(id, new IllegalArgumentException("ID is null"));
    return getRepository().findOne(id);
  }

  public List<Video> findAll() {
    return getRepository().findAll();
  }

  public Video save(Video entity) {
    notNull(entity, new IllegalArgumentException("Entity is null"));
    isNull(entity.getId(), new IllegalArgumentException("ID of entity is set"));
    validateEntity(entity);
    return getRepository().save(entity);
  }

  public Video update(Video entity) throws NoEntityToUpdateFoundException {
    notNull(entity, new IllegalArgumentException("Entity is null"));
    notNull(entity.getId(), new IllegalArgumentException("ID is null"));
    validateEntity(entity);
    if (!getRepository().exists(entity.getId())) {
      throw new NoEntityToUpdateFoundException("Can't find the entity to update");
    } else {
      return getRepository().save(entity);
    }
  }

  public void delete(Long id) throws NoEntityToDeleteFoundException {
    notNull(id, new IllegalArgumentException("ID is null"));
    if (!getRepository().exists(id)) {
      throw new NoEntityToDeleteFoundException("Can't find the entity to delete");
    } else {
      getRepository().delete(id);
    }
  }


  public Video findByCode(String code) {
    return getRepository().findByCode(code);
  }

//  public Video findByCodeDetached(String code) {
//    Video video = this.findByCode(code);
//
//    if (video != null) {
//      getRepository().em.detach(video);
//    }
//
//    return video;
//  }

  public Video findInCategory(Long categoryid, Long videoid) {
    return getRepository().findInCategory(categoryid, videoid);
  }

  public Video findInCategoryAndPlaylist(Long categoryid, Long playlistid, Long videoid) {
    return getRepository().findInCategoryAndPlaylist(categoryid, playlistid, videoid);
  }

  public Video findInPlaylist(Long playlistid, Long videoid) {
    return getRepository().findInPlaylist(playlistid, videoid);
  }

  public Video findByPlaylistAndSlug(long playlistid, String slug) {
    return getRepository().findByPlaylistAndSlug(playlistid, slug);
  }

  public List<Video> findAllInPlaylist(Long playlistid) {
    return getRepository().findAllInPlaylist(playlistid);
  }

  public Set<Video> findAllInCategoryAndPlaylist(Long categoryid, Long playlistid) {
    return getRepository().findAllInCategoryAndPlaylist(categoryid, playlistid);
  }


}
