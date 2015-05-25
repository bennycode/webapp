package com.welovecoding.data.tutorial.entity;

public enum Difficulty {

  EASY("easy"),
  MEDIUM("medium"),
  HARD("hard");

  private final String difficulty;

  private Difficulty(String difficulty) {
    this.difficulty = difficulty;
  }

  public String getDifficulty() {
    return difficulty;
  }

}
