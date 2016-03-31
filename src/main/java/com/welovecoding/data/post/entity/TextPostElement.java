package com.welovecoding.data.post.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class TextPostElement extends PostElement {

    @NotNull
    @Size(min = 1, max = 255)
    @Basic(optional = false)
    private String title;

    @Lob
    private String text;

    public TextPostElement() {
    }

    @Override
    public Comparable comparableAttribute() {
        return getTitle();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
