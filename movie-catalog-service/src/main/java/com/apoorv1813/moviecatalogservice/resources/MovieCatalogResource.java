package com.apoorv1813.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apoorv1813.moviecatalogservice.models.CatalogItem;
import com.apoorv1813.moviecatalogservice.models.Movie;
import com.apoorv1813.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping("{userId}")
  public List<CatalogItem> getCatalog(
      @PathVariable("userId")
      String userId) {
    // ratings of movieIds
    List<Rating> ratings = Arrays.asList(new Rating(1, 3), new Rating(2, 4), new Rating(3, 2));

    // details of movies
    return ratings.stream().map(rating -> {
      Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(),
          Movie.class);
      assert movie != null;
      return new CatalogItem(movie.getName(), "description", rating.getRating());
    }).collect(Collectors.toList());
  }
}
