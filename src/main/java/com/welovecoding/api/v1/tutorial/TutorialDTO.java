package com.welovecoding.api.v1.tutorial;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.welovecoding.api.v1.base.SlugBaseDTO;
import com.welovecoding.data.tutorial.entity.Difficulty;
import com.welovecoding.data.tutorial.entity.LanguageCode;

import java.util.Date;
import java.util.List;

@JsonPropertyOrder({})
public class TutorialDTO extends SlugBaseDTO<Long> {

    private String title;

    private String description;

    private boolean enabled;

    private List<AuthorDTO> authors;

    private LanguageCode languageCode;

    private Difficulty difficulty;

    public TutorialDTO(Long identifier, Date created, Date lastModified, String slug, String title, String description,
            boolean enabled, List<AuthorDTO> authors, LanguageCode languageCode, Difficulty difficulty) {
        super(identifier, created, lastModified, slug);
        this.title = title;
        this.description = description;
        this.enabled = enabled;
        this.authors = authors;
        this.languageCode = languageCode;
        this.difficulty = difficulty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }

    public LanguageCode getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(LanguageCode languageCode) {
        this.languageCode = languageCode;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
