package com.apoorv1813.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apoorv1813.moviecatalogservice.models.CatalogItem;
import com.apoorv1813.moviecatalogservice.models.Movie;
import com.apoorv1813.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
  @Autowired
  private RestTemplate restTemplate;

  //  @Autowired
  //  private WebClient.Builder webClientBuilder;

  @RequestMapping("{userId}")
  public List<CatalogItem> getCatalog(
      @PathVariable("userId")
      String userId) {
    UserRating userRating =
        restTemplate.getForObject("http://localhost:8083/ratingsData/users/" + userId,
            UserRating.class);
    assert userRating != null;
    return userRating.getUserRating().stream().map(rating -> {
      Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(),
          Movie.class);
      //      WebClient.Builder way
      //      Movie movie =
      //          webClientBuilder.build().get().uri("http://localhost:8082/movies/" + rating.getMovieId())
      //              .retrieve().bodyToMono(Movie.class).block();
      assert movie != null;
      return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
    }).collect(Collectors.toList());
  }
}
