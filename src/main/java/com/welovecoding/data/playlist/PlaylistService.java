package com.welovecoding.data.playlist;

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
public class PlaylistService {

  private final PlaylistRepository playlistRepository;

  @Autowired
  public PlaylistService(PlaylistRepository playlistRepository) {
    this.playlistRepository = playlistRepository;
  }

  private PlaylistRepository getRepository() {
    return playlistRepository;
  }

  public Playlist findOne(Long id) {
    notNull(id, new IllegalArgumentException("ID is null"));
    return getRepository().findOne(id);
  }

  public List<Playlist> findAll() {
    return getRepository().findAll();
  }

  public Playlist save(Playlist entity) {
    notNull(entity, new IllegalArgumentException("Entity is null"));
    isNull(entity.getId(), new IllegalArgumentException("ID of entity is set"));
    validateEntity(entity);
    return getRepository().save(entity);
  }

  public Playlist update(Playlist entity) throws NoEntityToUpdateFoundException {
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

  /**
   * Needed for YouTube importer.
   *
   * @param code Playlist ID, Example: PLpyrjJvJ7GJ4i-2v0kVohE1cWJs12TjDF
   * @return Playlist
   */
//  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
//  public Playlist getDetachedPlaylistByCode(String code) {
//    return getRepository().findByCode(code);
//  }
  public Playlist findByCode(String code) {
    return getRepository().findByCode(code);
  }

  public Playlist findInCategory(Long categoryid, Long playlistid) {
    return getRepository().findInCategory(categoryid, playlistid);
  }

//  public Playlist findByCodeDetached(String code) {
//    Playlist playlist = this.findByCode(code);
//    getRepository().em.detach(playlist);
//    return playlist;
//  }

  public Playlist findByCategoryAndSlug(Long categoryid, String slug) {
    return getRepository().findByCategoryAndSlug(categoryid, slug);
  }

  public Set<Playlist> findAllInCategory(Long categoryid) {
    return getRepository().findAllInCategory(categoryid);
  }

}
