package com.apenasgabs.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apenasgabs.blog.model.Posting;

public interface PostingRepository extends JpaRepository<Posting, Long> {

  
}