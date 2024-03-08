package com.apenasgabs.blog.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_posting")
public class Posting {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Title is required")
  @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
  private String title;
  
  @NotBlank(message = "Content is required")
  @Size(min = 5, max = 1000, message = "Content must be between 5 and 1000 characters")
  private String content;

  @UpdateTimestamp
  private LocalDateTime lastUpdate; 

  // public Posting() {
  // }

  // public Posting(String title, String content) {
  //   this.title = title;
  //   this.content = content;
  // }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getData() {
    return lastUpdate;
  }

  public void setData(LocalDateTime newDate) {
    this.lastUpdate = newDate;
  }

}
