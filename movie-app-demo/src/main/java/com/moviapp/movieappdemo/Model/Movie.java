package com.moviapp.movieappdemo.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie")
public class Movie{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    ///foreign key
    @ManyToOne(cascade = CascadeType.MERGE) //persisten object error solved with merge
    @JoinColumn(name = "id_category", referencedColumnName = "id" ,  nullable = false)
    private Category category;

    private String director;
    private double amount;

}
