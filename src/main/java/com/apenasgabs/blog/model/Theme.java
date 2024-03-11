package com.apenasgabs.blog.model;

import jakarta.persistence.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_theme")
public class Theme {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;

  @NotNull(message = "You must write a description")
  private String description;

  @OneToMany(mappedBy = "theme", cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties("theme")
  private List<Posting> posts;


  public Long getId() {
    return this.Id;
  }

  public void setId(Long Id) {
    this.Id = Id;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Posting> getPosts() {
    return this.posts;
  }

  public void setPosts(List<Posting> posts) {
    this.posts = posts;
  }

}
