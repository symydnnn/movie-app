package com.moviapp.movieappdemo.DTO;

import com.moviapp.movieappdemo.Model.Category;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class MovieDTO implements Serializable
{
  @Serial
  private static final long serialVersionUID = 3571692669728764356L;

  private Long id;

  private String name;

  private Category category;

  private String director;

  private double amount;


}
