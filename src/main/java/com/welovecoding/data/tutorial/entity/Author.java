package com.welovecoding.data.tutorial.entity;

import com.welovecoding.data.base.SlugBaseEntity;
import com.welovecoding.data.user.entity.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Author extends SlugBaseEntity<Long> {

    @Size(min = 0, max = 255)
    private String firstname;
    @Size(min = 0, max = 255)
    private String lastname;
    @Size(min = 0, max = 1024)
    @Column(length = 1024)
    private String description;
    @Size(min = 0, max = 255)
    private String website;
    @Size(min = 0, max = 255)
    private String channelUrl;
//  @OneToOne
//  private User user;

    public Author() {
    }

    public Author(Long id, Date created, Date lastModified, String slug, String firstname, String lastname, String description,
            String website, String channelUrl, User user) {
        super(id, created, lastModified, slug);
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
        this.website = website;
        this.channelUrl = channelUrl;
//    this.user = user;
    }

    @Override
    public Comparable comparableAttribute() {
        return getFirstname() + " " + getLastname();
    }

    @Override
    protected String getSlugableName() {
        return getFirstname() + " " + getLastname();
    }

//  public User getUser() {
//    return user;
//  }
//
//  public void setUser(User user) {
//    this.user = user;
//  }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
