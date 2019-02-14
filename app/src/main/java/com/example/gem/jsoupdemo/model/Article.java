package com.example.gem.jsoupdemo.model;

import java.io.Serializable;

public class Article implements Serializable {

  private String title;
  private String url;
  private String imageUrl;
  private String description;

  public Article() {
  }

  public Article(String title, String url, String imageUrl, String description) {
    this.title = title;
    this.url = url;
    this.imageUrl = imageUrl;
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
