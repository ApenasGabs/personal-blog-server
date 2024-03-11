package com.apenasgabs.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.apenasgabs.blog.model.Posting;

public interface PostingRepository extends JpaRepository<Posting, Long> {
  
  List<Posting> findAllByTitleContainingIgnoreCase(@Param("title") String title);
  
}