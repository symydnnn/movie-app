package com.moviapp.movieappdemo.Service;

import com.moviapp.movieappdemo.Model.Movie;

import java.util.List;

public interface IMovieService
{
  List<Movie> getMovies();

  Movie saveMovie(Movie movie);

  boolean deleteMovie(Long id);

  Movie updateMovie(Movie movie);

  Movie updateMovieAmount(Long movieId, double amount);

  Movie getMovieByName(String name);

  Movie getMoviebyId(Long id);

  List<Movie> findMoviesByCategoryId(Long categoryId);

}
