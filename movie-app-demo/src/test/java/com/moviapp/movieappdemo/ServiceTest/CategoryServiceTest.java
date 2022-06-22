package com.moviapp.movieappdemo.ServiceTest;

import com.moviapp.movieappdemo.Model.Category;
import com.moviapp.movieappdemo.Repository.ICategoryRepository;
import com.moviapp.movieappdemo.Service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest
{

  @Autowired
  CategoryService categoryService;

  @Mock  //test çalıştırılmadan önce sahte parametre veya nesne bildirir.
  private ICategoryRepository categoryRepository;

  @Test
  public void getCategories() {
    Category category= new Category();
    category.setId(1L);
    category.setCategoryType("Aksiyon");



    when(categoryRepository.findAll()).thenReturn(Stream.of(category).collect(Collectors.toList()));
    Assertions.assertEquals(1, categoryService.getCategories().size());
  }

  @Test
  public void getCategorybyId()
  {
    Long id =1L;
    Assertions.assertEquals(false,categoryService.getCategorybyId(id).getId().toString().isEmpty());
  }

  @Test
  public void saveCategory()
  {
    Category category = new Category();
    category.setCategoryType("Korku");
    Assertions.assertEquals(false,categoryService.saveCategory(category).getId().toString().isEmpty());

  }

  @Test
  public void deleteCategory()
  {
    Long categoryId = 1L;
    boolean exp = categoryService.deleteCategory(categoryId);
    Assertions.assertEquals(1,exp);
  }

  @Test
  public void updateCategory()
  {
    Category category = new Category();
    category.setId(1L);
    category.setCategoryType("Şiddet");
    Assertions.assertEquals(false,categoryService.updateCategory(category).getId().toString().isEmpty());

  }

}
