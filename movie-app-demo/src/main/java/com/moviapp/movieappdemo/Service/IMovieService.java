package com.moviapp.movieappdemo.Service;

import com.moviapp.movieappdemo.Model.Movie;

import java.util.List;

public interface IMovieService {

    List<Movie> getMovies();

    Movie saveMovie(Movie movie) ;

    boolean deleteMovie(Long movie);

    Movie updateMovie(Movie movie);

    void updateMovieAmount(Movie movie);

    Movie findByName(String name);

}
