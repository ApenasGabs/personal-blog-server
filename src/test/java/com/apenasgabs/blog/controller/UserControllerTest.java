package com.apenasgabs.blog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.apenasgabs.blog.model.User;
import com.apenasgabs.blog.repository.UserRepository;
import com.apenasgabs.blog.service.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @BeforeAll
  void start() {

    userRepository.deleteAll();

    userService.registerUser(new User(0L, "Root", "root@root.com", "rootroot", "-"));

  }

  @Test
  @DisplayName("Register a User")
  public void shouldCreateAUser() {

    HttpEntity<User> requestEntity = new HttpEntity<User>(
        new User(0L, "John Henry", "john_henry@email.com", "13465278", "-"));

    ResponseEntity<User> responseEntity = testRestTemplate.exchange("/user/register", HttpMethod.POST,
        requestEntity, User.class);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

  }

  @Test
  @DisplayName("Should not allow duplicate User")
  public void shouldNotDuplicateUser() {

    userService.registerUser(new User(0L, "Mary Silva", "mary_silva@email.com", "13465278", "-"));

    HttpEntity<User> requestEntity = new HttpEntity<User>(
        new User(0L, "Mary Silva", "mary_silva@email.com", "13465278", "-"));

    ResponseEntity<User> responseEntity = testRestTemplate.exchange("/user/register", HttpMethod.POST,
        requestEntity, User.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }

  @Test
  @DisplayName("Update a User")
  public void shouldUpdateAUser() {

    Optional<User> registeredUser = userService.registerUser(
        new User(0L, "Juliana Andrews", "juliana_andrews@email.com", "juliana123", "-"));

    User userUpdate = new User(registeredUser.get().getId(), "Juliana Andrews Ramos",
        "juliana_ramos@email.com", "juliana123", "-");

    HttpEntity<User> requestEntity = new HttpEntity<User>(userUpdate);

    ResponseEntity<User> responseEntity = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
        .exchange("/user/update", HttpMethod.PUT, requestEntity, User.class);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

  }

  @Test
  @DisplayName("List all Users")
  public void shouldListAllUsers() {

    userService.registerUser(
        new User(0L, "Sabrina Sanchez", "sabrina_sanchez@email.com", "sabrina123", "-"));

    userService.registerUser(
        new User(0L, "Richard Marks", "richard_marks@email.com", "richard123", "-"));

    ResponseEntity<String> response = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
        .exchange("/user/all", HttpMethod.GET, null, String.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());

  }

}
