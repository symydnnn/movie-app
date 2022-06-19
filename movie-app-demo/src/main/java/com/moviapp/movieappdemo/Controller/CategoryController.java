package com.moviapp.movieappdemo.Controller;

import com.moviapp.movieappdemo.Model.Category;
import com.moviapp.movieappdemo.Model.Movie;
import com.moviapp.movieappdemo.Service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category saveCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @GetMapping
    public List<Category> getCategories()
    {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@RequestParam long id)
    {
        return categoryService.getCategorybyId(id);
    }

    @DeleteMapping
    public boolean deleteCategory(@RequestParam long categoryId)
    {
        return categoryService.deleteCategory(categoryId);
    }

    @PutMapping
    public void updateCategory(@RequestBody Category category)
    {
        categoryService.updateCategory(category);
    }


}
