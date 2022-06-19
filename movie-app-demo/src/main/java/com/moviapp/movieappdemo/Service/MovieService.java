package com.moviapp.movieappdemo.Service;

import com.moviapp.movieappdemo.Model.Movie;
import com.moviapp.movieappdemo.Repository.IMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class MovieService implements IMovieService {

    @Autowired
    private final IMovieRepository movieRepository;

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public boolean deleteMovie(Long movieId) {
        if(movieRepository.existsById(movieId))
        {
            movieRepository.deleteById(movieId);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public Movie updateMovie(Movie movie) {
        if(movieRepository.existsById(movie.getId()))
        {
            return movieRepository.save(movie);
        }
        return null;
    }

    @Override
    public void updateMovieAmount(Movie movie) {

    }

    @Override
    public Movie findByName(String name) {
        return null;
    }
}
