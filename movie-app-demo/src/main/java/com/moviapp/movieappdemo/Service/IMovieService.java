package com.moviapp.movieappdemo.Service;

import com.moviapp.movieappdemo.DTO.MovieDTO;

import java.util.List;

public interface IMovieService
{
  List<MovieDTO> getMovies();

  MovieDTO saveMovie(MovieDTO movieDTO);

  boolean deleteMovie(Long id);

  MovieDTO updateMovie(MovieDTO movieDTO);

  MovieDTO updateMovieAmount(Long movieId, double amount);

  MovieDTO getMovieByName(String name);

  MovieDTO getMoviebyId(Long id);

  List<MovieDTO> findMoviesByCategoryId(Long categoryId);

}
