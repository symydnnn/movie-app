package com.moviapp.movieappdemo.Service;

import com.moviapp.movieappdemo.Model.Category;
import com.moviapp.movieappdemo.Model.Movie;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ICategoryService {

    List<Category> getCategories();

    Category saveCategory(Category category);

    boolean deleteCategory(Long category);

    Category updateCategory(Category category);

    List<Movie> findMoviesByCategoryName(String name);

}
