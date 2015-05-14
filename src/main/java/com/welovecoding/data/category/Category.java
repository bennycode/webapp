package com.welovecoding.data.category;

import com.welovecoding.data.base.BaseEntity;
import com.welovecoding.data.playlist.entity.Playlist;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends BaseEntity<Long> {

  @NotNull
  @Size(min = 1, max = 7)
  @Basic(optional = false)
  private String color;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
  private List<Playlist> playlists;

  public Category() {
    this.playlists = new ArrayList<>();
    this.color = "#000000";
  }

  public Category(String color) {
    this();
    this.color = color;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public List<Playlist> getPlaylists() {
    return playlists;
  }

  public void setPlaylists(List<Playlist> playlists) {
    this.playlists = playlists;
  }
}
