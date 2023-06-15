package co.jeel.movieapp.repositories;

import co.jeel.movieapp.entities.Movie;
import co.jeel.movieapp.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {

}
