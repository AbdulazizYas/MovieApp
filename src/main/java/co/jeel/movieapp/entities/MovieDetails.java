package co.jeel.movieapp.entities;

import javax.persistence.*;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table
public class MovieDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String desc;
    private LocalDate release_date;
    private Integer duration;
    private Double rating;

    @OneToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

}