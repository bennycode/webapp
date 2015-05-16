package com.welovecoding.api.v1.video;

import com.welovecoding.api.v1.playlist.PlaylistDTO;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({
  "id",
  "name",
  "description",
  "code",
  "previewImageUrl",
  "downloadUrl",
  "permalink"
})
public class VideoDTO {

  private Long id;
  private String name;
  private String description;
  private String code;
  private String previewImageUrl;
  private String downloadUrl;
  private String permalink;
  @JsonIgnore
  private PlaylistDTO playlist;

  public VideoDTO() {
  }

  VideoDTO(Long id, String name, String description, String code, String previewImageUrl, String downloadUrl, String permalink, PlaylistDTO playlist) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.code = code;
    this.previewImageUrl = previewImageUrl;
    this.downloadUrl = downloadUrl;
    this.permalink = permalink;
    this.playlist = playlist;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null || name.isEmpty()) {
    } else {
      this.name = name;
    }
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if (description == null || description.isEmpty()) {
    } else {
      this.description = description;
    }
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    if (code == null || code.isEmpty()) {
    } else {
      this.code = code;
    }
  }

  public String getPreviewImageUrl() {
    return previewImageUrl;
  }

  public void setPreviewImageUrl(String previewImageUrl) {
    if (previewImageUrl == null || previewImageUrl.isEmpty()) {
    } else {
      this.previewImageUrl = previewImageUrl;
    }
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public void setDownloadUrl(String downloadUrl) {
    if (downloadUrl == null || downloadUrl.isEmpty()) {
    } else {
      this.downloadUrl = downloadUrl;
    }
  }

  public String getPermalink() {
    return this.permalink;
  }

  public void setPermalink(String permalink) {
    if (permalink == null || permalink.isEmpty()) {
    } else {
      this.permalink = permalink;
    }
  }

  public PlaylistDTO getPlaylist() {
    return playlist;
  }

  public void setPlaylist(PlaylistDTO playlist) {
    this.playlist = playlist;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    VideoDTO videoDTO = (VideoDTO) o;

    if (id != null ? !id.equals(videoDTO.id) : videoDTO.id != null) return false;
    if (name != null ? !name.equals(videoDTO.name) : videoDTO.name != null) return false;
    if (description != null ? !description.equals(videoDTO.description) : videoDTO.description != null) return false;
    if (code != null ? !code.equals(videoDTO.code) : videoDTO.code != null) return false;
    if (previewImageUrl != null ? !previewImageUrl.equals(videoDTO.previewImageUrl) : videoDTO.previewImageUrl != null)
      return false;
    if (downloadUrl != null ? !downloadUrl.equals(videoDTO.downloadUrl) : videoDTO.downloadUrl != null) return false;
    if (permalink != null ? !permalink.equals(videoDTO.permalink) : videoDTO.permalink != null) return false;
    return !(playlist != null ? !playlist.equals(videoDTO.playlist) : videoDTO.playlist != null);

  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (code != null ? code.hashCode() : 0);
    result = 31 * result + (previewImageUrl != null ? previewImageUrl.hashCode() : 0);
    result = 31 * result + (downloadUrl != null ? downloadUrl.hashCode() : 0);
    result = 31 * result + (permalink != null ? permalink.hashCode() : 0);
    result = 31 * result + (playlist != null ? playlist.hashCode() : 0);
    return result;
  }
}
