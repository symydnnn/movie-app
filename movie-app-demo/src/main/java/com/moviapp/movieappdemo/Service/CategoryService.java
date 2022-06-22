package com.moviapp.movieappdemo.Service;

import com.moviapp.movieappdemo.Exception.ResourceNotFoundException;
import com.moviapp.movieappdemo.Model.Category;
import com.moviapp.movieappdemo.Model.Movie;
import com.moviapp.movieappdemo.Repository.ICategoryRepository;
import com.moviapp.movieappdemo.Repository.IMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService
{
  private static final String REDIS_CACHE_VALUE = "categoryCache";

  private final ICategoryRepository categoryRepository;

  private final IMovieRepository movieRepository;

  @Override
  @Cacheable(value = REDIS_CACHE_VALUE)
  public List<Category> getCategories()
  {
    return categoryRepository.findAll();
  }

  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#id")
  public Category getCategorybyId(Long id)
  {
    Category c = getCategories().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    if(c == null){
      throw new ResourceNotFoundException("ID'ye sahip kategori bulunamadi.");
    }
    return c;
  }

  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#category.id")
  public Category saveCategory(Category category)
  {
    return categoryRepository.save(category);
  }

  @Override
  @CacheEvict(value = REDIS_CACHE_VALUE, key = "#categoryId")
  public boolean deleteCategory(Long categoryId)
  {
    List<Movie> moviesList = movieRepository.findAll();

    if (categoryRepository.existsById(categoryId))
    {
      for(int i=0; i < moviesList.size();i++ ){
        if(moviesList.get(i).getCategory().getId() == categoryId){
          movieRepository.deleteById(moviesList.get(i).getId());
        }
      }

      categoryRepository.deleteById(categoryId);
      return true;
    } else
    {
      return false;
    }
  }

  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#category.id")
  public Category updateCategory(Category category)
  {
    if (categoryRepository.existsById(category.getId()))
    {
      return categoryRepository.save(category);
    }
    throw new ResourceNotFoundException("Kategori bulunamadı.");
  }

  @CacheEvict(cacheNames = REDIS_CACHE_VALUE, allEntries = true)
  public void clearCache(){
    System.out.println("cache temizlendi");
  }

}
