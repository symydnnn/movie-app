package com.moviapp.movieappdemo.Repository;

import com.moviapp.movieappdemo.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {

}
