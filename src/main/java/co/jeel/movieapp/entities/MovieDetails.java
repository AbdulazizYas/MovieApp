package co.jeel.movieapp.entities;

import javax.persistence.*;


import lombok.*;

import java.time.LocalDate;


@Setter
@ToString
@Entity
@Table
public class MovieDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String desc;

    @Getter
    private LocalDate release_date;

    @Getter
    private Integer duration;

    @Transient
    private Double rating;

    @OneToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    @Getter
    private Movie movie;


    public Double getRating(){
        Movie m = this.getMovie();

        int count = 0;
        double ratingSum = 0.0;

        if (m.getReviews() == null){
            return 0.0;
        }
        for (Review review : m.getReviews()){
            ratingSum += review.getRating();
            count += 1;
        }

        if (count == 0) return 0.00;

        return Math.round((ratingSum/count) * 100)/100.0;
    }
}