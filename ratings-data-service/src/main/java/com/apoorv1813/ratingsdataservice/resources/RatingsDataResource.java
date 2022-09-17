package com.apoorv1813.ratingsdataservice.resources;

import com.apoorv1813.ratingsdataservice.models.Rating;

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
}
