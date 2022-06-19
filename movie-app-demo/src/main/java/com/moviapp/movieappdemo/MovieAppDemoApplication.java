package com.moviapp.movieappdemo;

import com.moviapp.movieappdemo.Controller.CategoryController;
import com.moviapp.movieappdemo.Controller.MovieController;
import com.moviapp.movieappdemo.Model.Category;
import com.moviapp.movieappdemo.Model.Movie;
import com.moviapp.movieappdemo.Service.CategoryService;
import com.moviapp.movieappdemo.Service.ICategoryService;
import com.moviapp.movieappdemo.Service.IMovieService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieAppDemoApplication implements CommandLineRunner {

	@Autowired
	private MovieController movieController;
	@Autowired
	private CategoryController categoryController;
	@Autowired
	private CategoryService categoryService;


	public static void main(String[] args) {
		SpringApplication.run(MovieAppDemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Movie m = new Movie();
		m.setCategory(categoryController.getCategoryById(1));
		m.setName("Cruella");
		m.setAmount(15);
		m.setDirector("Craigh Gillespie");
		movieController.saveMovie(m);
	}

}
