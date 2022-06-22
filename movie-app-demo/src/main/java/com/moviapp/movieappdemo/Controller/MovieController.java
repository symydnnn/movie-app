package com.moviapp.movieappdemo.Controller;

import com.moviapp.movieappdemo.Model.Movie;
import com.moviapp.movieappdemo.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class MovieController
{
  @Autowired
  private MovieService movieService;

  private int sayac = 0;

  public MovieController(MovieService movieService)
  {
    this.movieService = movieService;
  }

  @PostMapping("movie")
  @ResponseBody
  public Movie saveMovie(@RequestBody Movie movie)
  {
    return movieService.saveMovie(movie);
  }

  @GetMapping("movie")
  public List<Movie> getMovies()
  {
    if((sayac %5 )== 0){
      movieService.clearCache();
    }
    sayac++;
    return movieService.getMovies();
  }

  @GetMapping("movie/id/{id}")
  public Movie getMovieById(@PathVariable long id)
  {
    return movieService.getMoviebyId(id);
  }

  @GetMapping("movie/name")
  public Movie getMovieByName(@RequestParam String name)
  {
    return movieService.getMovieByName(name);
  }


  @DeleteMapping("movie")
  public boolean deleteMovie(@RequestParam Long movieId)
  {
    movieService.deleteMovie(movieId);
    return true;
  }

  @PutMapping("movie")
  public void updateMovie(@RequestBody Movie movie)
  {
    movieService.updateMovie(movie);
  }

  @PutMapping("movie/amount")
  public void updateMovieAmount(@RequestParam long movieId, double amount){
    movieService.updateMovieAmount(movieId, amount);
  }

  @GetMapping("movie/categories")
  public List<Movie> findMoviesByCategoryId(@RequestParam long categoryId){
    return movieService.findMoviesByCategoryId(categoryId);}




}
