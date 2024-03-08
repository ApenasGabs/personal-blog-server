package com.apenasgabs.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_posting")
public class Posting {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

}
