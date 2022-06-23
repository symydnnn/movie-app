package com.moviapp.movieappdemo.DTO;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable
{
  @Serial
  private static final long serialVersionUID = -8122182755654786170L;

  private Long id;

  private String categoryType;

}
