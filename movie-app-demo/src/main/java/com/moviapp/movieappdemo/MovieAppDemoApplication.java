package com.moviapp.movieappdemo;

import com.moviapp.movieappdemo.Controller.CategoryController;
import com.moviapp.movieappdemo.Controller.MovieController;
import com.moviapp.movieappdemo.Model.Movie;
import com.moviapp.movieappdemo.Service.CategoryService;
import com.moviapp.movieappdemo.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableCaching
public class MovieAppDemoApplication
{

	public static void main(String[] args) {
		SpringApplication.run(MovieAppDemoApplication.class, args);
	}

}
