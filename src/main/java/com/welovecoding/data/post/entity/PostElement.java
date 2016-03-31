package com.welovecoding.data.post.entity;

import com.welovecoding.data.base.BaseEntity;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class PostElement extends BaseEntity<Long> {

    @ManyToOne
    private Post post;

    public PostElement() {
    }

    protected PostElement(Long id, Date created, Date lastModified, Post post) {
        super(id, created, lastModified);
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
