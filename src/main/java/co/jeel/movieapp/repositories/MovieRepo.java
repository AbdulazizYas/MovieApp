package co.jeel.movieapp.repositories;


import co.jeel.movieapp.DTOs.MovieDto;
import co.jeel.movieapp.entities.Genre;
import co.jeel.movieapp.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import co.jeel.movieapp.entities.Movie;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie,Long>{

    Movie findMovieByReviewsContains(Review review);
}
