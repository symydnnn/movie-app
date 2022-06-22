package com.moviapp.movieappdemo.Service;

import com.moviapp.movieappdemo.Exception.ResourceNotFoundException;
import com.moviapp.movieappdemo.Model.Movie;
import com.moviapp.movieappdemo.Repository.IMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class MovieService implements IMovieService
{
  private final IMovieRepository movieRepository;

  private static final String REDIS_CACHE_VALUE = "movieCache";

  @Override
  @Cacheable(value = REDIS_CACHE_VALUE)
  public List<Movie> getMovies()
  {
    return movieRepository.findAll();
  }

  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#id")
  public Movie getMoviebyId(Long id)
  {
    Movie movie = getMovies().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    if(movie == null){
      throw new ResourceNotFoundException("ID'ye sahip film bulunamadı");
    }
    return movie;
  }

  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#name")
  public Movie getMovieByName(String name)
  {
    Movie movie = getMovies().stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    if(movie == null){
      throw new ResourceNotFoundException("Isme sahip film bulunamadı");
    }
    return movie;
  }

  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#movie.id")
  public Movie saveMovie(Movie movie)
  {
    return movieRepository.save(movie);

  }

  @Override
  @CacheEvict(value = REDIS_CACHE_VALUE, key = "#movieId")
  public boolean deleteMovie(Long movieId)
  {
    if (movieRepository.existsById(movieId))
    {
      movieRepository.deleteById(movieId);
      return true;
    } else
    {
      return false;
    }
  }

  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#movie.id")
  public Movie updateMovie(Movie movie)
  {
    if (movieRepository.existsById(movie.getId()))
    {
      return movieRepository.save(movie);
    }
    throw new ResourceNotFoundException("ID'ye sahip film bulunamadi.");
  }

  @Override
  @CachePut(value = REDIS_CACHE_VALUE)
  public Movie updateMovieAmount(Long movieId, double amount)
  {
    Movie newMovie = getMovies().stream().filter(p -> p.getId() == movieId).findFirst().orElse(null);
    if (newMovie != null)
    {
      newMovie.setAmount(amount);
      return movieRepository.save(newMovie);
    }
    throw new ResourceNotFoundException("ID'ye sahip film bulunamadi.");
  }


  @Override
  @Cacheable(value = REDIS_CACHE_VALUE)
  public List<Movie> findMoviesByCategoryId(Long categoryId)
  {
      List<Movie> movieList = getMovies();
      List<Movie> newMovieList = new ArrayList<Movie>();

      for (int i = 0; i < movieList.size(); i++)
      {
        Movie movie = movieList.get(i);
        if(movieList.get(i).getCategory().getId() == categoryId)
        {
          newMovieList.add(movie);
        }
      }
      if (newMovieList.size() != 0)
      {
        return newMovieList;
      }
      throw new ResourceNotFoundException("ID'ye sahip filmler bulunamadi.");
    }


    @CacheEvict(cacheNames = REDIS_CACHE_VALUE, allEntries = true)
    public void clearCache(){
    System.out.println("cache temizlendi");
    }

}