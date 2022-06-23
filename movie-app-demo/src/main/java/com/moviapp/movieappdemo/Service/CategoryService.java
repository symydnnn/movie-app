package com.moviapp.movieappdemo.Service;

import com.moviapp.movieappdemo.DTO.CategoryDTO;
import com.moviapp.movieappdemo.DTO.MovieDTO;
import com.moviapp.movieappdemo.Exception.ResourceNotFoundException;
import com.moviapp.movieappdemo.Model.Category;
import com.moviapp.movieappdemo.Model.Movie;
import com.moviapp.movieappdemo.Repository.ICategoryRepository;
import com.moviapp.movieappdemo.Repository.IMovieRepository;
import com.moviapp.movieappdemo.Util.MappingHelper;
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
  public List<CategoryDTO> getCategories()
  {
    List<Category> categoryList = categoryRepository.findAll();

    return MappingHelper.mapList(categoryList, CategoryDTO.class);
  }

  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#id")
  public CategoryDTO getCategorybyId(Long id)
  {
    CategoryDTO categoryDTO = getCategories().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    if(categoryDTO == null){
      throw new ResourceNotFoundException("ID'ye sahip kategori bulunamadi.");
    }
    return categoryDTO;
  }

  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#categoryDTO.categoryType")
  public CategoryDTO saveCategory(CategoryDTO categoryDTO)
  {
    Category category = MappingHelper.map(categoryDTO, Category.class);
    Category result = categoryRepository.save(category);
    return MappingHelper.map(result, CategoryDTO.class);
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
  @CachePut(value = REDIS_CACHE_VALUE, key = "#categoryDTO.categoryType")
  public CategoryDTO updateCategory(CategoryDTO categoryDTO)
  {
    if (categoryRepository.existsById(categoryDTO.getId()))
    {
      return saveCategory(categoryDTO);
    }
    throw new ResourceNotFoundException("Kategori bulunamadı.");
  }

  @CacheEvict(cacheNames = REDIS_CACHE_VALUE, allEntries = true)
  public void clearCache(){
    System.out.println("cache temizlendi");
  }

}
