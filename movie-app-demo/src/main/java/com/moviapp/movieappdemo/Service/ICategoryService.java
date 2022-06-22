package com.moviapp.movieappdemo.Service;

import com.moviapp.movieappdemo.Model.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getCategories();

    Category saveCategory(Category category);

    boolean deleteCategory(Long category);

    Category updateCategory(Category category);

    Category getCategorybyId(Long id);


}
