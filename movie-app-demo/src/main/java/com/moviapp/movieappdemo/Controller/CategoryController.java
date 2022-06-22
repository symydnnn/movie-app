package com.moviapp.movieappdemo.Controller;

import com.moviapp.movieappdemo.Model.Category;
import com.moviapp.movieappdemo.Service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    CategoryService categoryService;

    int categorysayac = 0;


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
      if((categorysayac % 5) == 0){
        categoryService.clearCache();
      }
      categorysayac++;
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable long id)
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
