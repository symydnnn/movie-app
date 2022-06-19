package com.moviapp.movieappdemo.Controller;

import com.moviapp.movieappdemo.Model.Movie;
import com.moviapp.movieappdemo.Service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService)
    {
        this.movieService = movieService;
    }

    @PostMapping("movie")
    @ResponseBody
    public Movie saveMovie(@RequestBody Movie movie){
        return movieService.saveMovie(movie);
    }

    @GetMapping("movie")
    public List<Movie> getMovies()
    {
        return movieService.getMovies();
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

}
