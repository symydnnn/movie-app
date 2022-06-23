package com.moviapp.movieappdemo.Service;

import com.moviapp.movieappdemo.DTO.MovieDTO;
import com.moviapp.movieappdemo.Exception.ResourceNotFoundException;
import com.moviapp.movieappdemo.Model.Movie;
import com.moviapp.movieappdemo.Repository.IMovieRepository;
import com.moviapp.movieappdemo.Util.MappingHelper;
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
import java.util.Map;

@Transactional
@RequiredArgsConstructor
@Service
public class MovieService implements IMovieService
{
  private final IMovieRepository movieRepository;

  private static final String REDIS_CACHE_VALUE = "movieCache";

  @Override
  @Cacheable(value = REDIS_CACHE_VALUE)
  public List<MovieDTO> getMovies()
  {
    List<Movie> movieList = movieRepository.findAll();
    return MappingHelper.mapList(movieList, MovieDTO.class);
  }

  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#id")
  public MovieDTO getMoviebyId(Long id)
  {
    MovieDTO movieDTO = getMovies().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    if(movieDTO == null){
      throw new ResourceNotFoundException("ID'ye sahip film bulunamadı");
    }
    return movieDTO;
  }

  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#name")
  public MovieDTO getMovieByName(String name)
  {
    MovieDTO movieDTO = getMovies().stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    if(movieDTO == null){
      throw new ResourceNotFoundException("Isme sahip film bulunamadı");
    }
    return movieDTO;
  }

  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#movieDTO.name")
  public MovieDTO saveMovie(MovieDTO movieDTO)
  {
    Movie movie = MappingHelper.map(movieDTO, Movie.class);
    Movie result = movieRepository.save(movie);
    MovieDTO movieDTO1 = getMovieByName(result.getName());
    return movieDTO1;

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
  @CachePut(value = REDIS_CACHE_VALUE, key = "#movieDTO.name")
  public MovieDTO updateMovie(MovieDTO movieDTO)
  {
    if (movieRepository.existsById(movieDTO.getId()))
    {
      Movie movie = MappingHelper.map(movieDTO, Movie.class);
      Movie result = movieRepository.save(movie);
      return MappingHelper.map(result, MovieDTO.class);
    }
    throw new ResourceNotFoundException("ID'ye sahip film bulunamadi.");
  }

  @Override
  @CachePut(value = REDIS_CACHE_VALUE)
  public MovieDTO updateMovieAmount(Long movieId, double amount)
  {
    MovieDTO newMovieDTO = getMovies().stream().filter(p -> p.getId() == movieId).findFirst().orElse(null);
    if (newMovieDTO != null)
    {
      newMovieDTO.setAmount(amount);
      Movie movie = MappingHelper.map(newMovieDTO, Movie.class);
      Movie result = movieRepository.save(movie);
      return MappingHelper.map(result, MovieDTO.class);
    }
    throw new ResourceNotFoundException("ID'ye sahip film bulunamadi.");
  }


  @Override
  @Cacheable(value = REDIS_CACHE_VALUE)
  public List<MovieDTO> findMoviesByCategoryId(Long categoryId)
  {
      List<MovieDTO> movieListDTO = getMovies();
      List<MovieDTO> newMovieListDTO = new ArrayList<MovieDTO>();

      for (int i = 0; i < movieListDTO.size(); i++)
      {
        MovieDTO movieDTO = movieListDTO.get(i);
        if(movieListDTO.get(i).getCategory().getId() == categoryId)
        {
          newMovieListDTO.add(movieDTO);
        }
      }
      if (newMovieListDTO.size() != 0)
      {
        return newMovieListDTO;
      }
      throw new ResourceNotFoundException("ID'ye sahip filmler bulunamadi.");
    }


    @CacheEvict(cacheNames = REDIS_CACHE_VALUE, allEntries = true)
    public void clearCache(){
    System.out.println("cache temizlendi");
    }

}