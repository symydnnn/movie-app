package com.moviapp.movieappdemo.Repository;

import com.moviapp.movieappdemo.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends JpaRepository<Movie,Long> {

}
