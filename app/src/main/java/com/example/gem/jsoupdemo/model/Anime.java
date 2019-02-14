package com.example.gem.jsoupdemo.model;

import java.io.Serializable;

public class Anime implements Serializable {

  private String urlBanner;
  private String name;
  private String episode;
  private String view;

  public Anime() {
  }

  public Anime(String urlBanner, String name, String episode, String view) {
    this.urlBanner = urlBanner;
    this.name = name;
    this.episode = episode;
    this.view = view;
  }

  public String getUrlBanner() {
    return urlBanner;
  }

  public void setUrlBanner(String urlBanner) {
    this.urlBanner = urlBanner;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEpisode() {
    return episode;
  }

  public void setEpisode(String episode) {
    this.episode = episode;
  }

  public String getView() {
    return view;
  }

  public void setView(String view) {
    this.view = view;
  }
}
