package com.moviapp.movieappdemo.Service;

import com.moviapp.movieappdemo.Model.Category;
import com.moviapp.movieappdemo.Model.Movie;
import com.moviapp.movieappdemo.Repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private final ICategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategorybyId(Long id) {
        Category c = getCategories().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        return c;
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public boolean deleteCategory(Long categoryId) {
        if(categoryRepository.existsById(categoryId))
        {
            categoryRepository.deleteById(categoryId);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public Category updateCategory(Category category) {
        if(categoryRepository.existsById(category.getId()))
        {
            return categoryRepository.save(category);
        }
        return null;
    }

    @Override
    public List<Movie> findMoviesByCategoryName(String name) {
        return null;
    }
}
