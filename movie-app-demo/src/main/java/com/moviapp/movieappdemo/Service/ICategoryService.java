package com.moviapp.movieappdemo.Service;

import com.moviapp.movieappdemo.DTO.CategoryDTO;

import java.util.List;

public interface ICategoryService {

    List<CategoryDTO> getCategories();

    CategoryDTO saveCategory(CategoryDTO categoryDTO);

    boolean deleteCategory(Long category);

    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategorybyId(Long id);


}
