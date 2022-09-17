package com.apoorv1813.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import com.apoorv1813.ratingsdataservice.models.Rating;
import com.apoorv1813.ratingsdataservice.models.UserRating;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsData")
public class RatingsDataResource {

  @RequestMapping("/{movieId}")
  public Rating getRating(
      @PathVariable("movieId")
      int movieId) {
    return new Rating(movieId, 1);
  }

  @RequestMapping("/users/{userId}")
  public UserRating getUserRating(
      @PathVariable("userId")
      int userId) {
    return new UserRating(List.of(new Rating(1, 3), new Rating(2, 4), new Rating(3, 2)));
  }
}
