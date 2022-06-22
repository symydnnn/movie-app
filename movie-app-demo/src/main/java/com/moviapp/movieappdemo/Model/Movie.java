package com.moviapp.movieappdemo.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie")
public class Movie implements Serializable
{
  @Serial
  private static final long serialVersionUID = 1712881272088835327L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  ///foreign key
  @ManyToOne(cascade = CascadeType.MERGE) //object error solved with merge
  @JoinColumn(name = "id_category", referencedColumnName = "id" ,  nullable = false)
  private Category category;

  private String director;
  private double amount;

}
