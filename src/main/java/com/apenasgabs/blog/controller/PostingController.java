package com.apenasgabs.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apenasgabs.blog.model.Posting;
import com.apenasgabs.blog.repository.PostingRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostingController {

  @Autowired
  private PostingRepository postingRepository;

  @GetMapping("/all")
  public ResponseEntity<List<Posting>> getAll() {
    return ResponseEntity.ok(postingRepository.findAll());
  }

  @PostMapping("/create")
  public ResponseEntity<Posting> createPost(@RequestBody Posting newPost) {
    Posting createdPost = postingRepository.save(newPost);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
  }

}
