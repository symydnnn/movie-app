package com.moviapp.movieappdemo.ServiceTest;


import com.moviapp.movieappdemo.DTO.MovieDTO;
import com.moviapp.movieappdemo.Model.Movie;
import com.moviapp.movieappdemo.Repository.IMovieRepository;
import com.moviapp.movieappdemo.Service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest
{
  @Autowired
  MovieService movieService;

  @Mock
  private IMovieRepository movieRepository;

  @Test
  public void getMovies() {
    Movie movie = new Movie();
    movie.setName("Kırmızı");
    movie.setDirector("Mahsun Kırmızı Gül");
    movie.setAmount(25);

    Movie movie2 = new Movie();
    movie2.setName("Dora");
    movie2.setDirector("Alfred Rich");
    movie2.setAmount(30);

    when(movieRepository.findAll()).thenReturn(Stream.of(movie2, movie).collect(Collectors.toList()));
    Assertions.assertEquals(2, movieService.getMovies().size());
  }

  @Test
  public void getMoviebyId()
  {
    long id = 1;
    Assertions.assertEquals(0,movieService.getMoviebyId(id).getId().toString().isEmpty());
  }

  @Test
  public void getMovieByName()
  {
    String name = "Titanik";
    Assertions.assertEquals(0,movieService.getMovieByName(name).getId().toString().isEmpty());
  }

  @Test
  public void saveMovie()
  {
    long categoryId = 1;
    MovieDTO movieDTO = new MovieDTO();
    movieDTO.setName("The Haunting of Hill House");
    movieDTO.setDirector("Mike Flanagan");
    //movieDTO.setCategory(categoryService.getCategorybyId(categoryId));
    movieDTO.setAmount(15);
    MovieDTO newMovieDTO = movieService.saveMovie(movieDTO);
    Assertions.assertEquals(0,newMovieDTO.getId().toString().isEmpty());
  }

  @Test
  public void deleteMovie()
  {
    long movieId = 1;
    boolean exp = movieService.deleteMovie(movieId);
    Assertions.assertEquals(false,exp);
  }

  @Test
  public void updateMovie()
  {
    MovieDTO movieDTO = new MovieDTO();
    long id = 1;
    movieDTO.setId(id);
    movieDTO.setName("Ayşecik");
    movieDTO.setDirector("Kemal Sunal");
    movieDTO.setAmount(15);
    MovieDTO newMovieDTO = movieService.updateMovie(movieDTO);
    Assertions.assertEquals(0,newMovieDTO.getId().toString().isEmpty());
  }

  @Test
  public void updateMovieAmount()
  {
    long movieId = 1;
    double amount = 25;
    Assertions.assertEquals(0,movieService.updateMovieAmount(movieId,amount).getId().toString().isEmpty());
  }

  @Test
  public void findMoviesByCategoryId()
  {
    long categoryId = 1;
    movieService.findMoviesByCategoryId(categoryId);
  }

}
