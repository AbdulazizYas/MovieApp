package co.jeel.movieapp.repositories;


import co.jeel.movieapp.entities.MovieDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDetailsRepo extends JpaRepository<MovieDetails,Long>{

}
