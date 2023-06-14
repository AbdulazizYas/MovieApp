package co.jeel.movieapp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import co.jeel.movieapp.entities.Movie;

public interface MovieRepo extends JpaRepository<Movie,Long>{

}
