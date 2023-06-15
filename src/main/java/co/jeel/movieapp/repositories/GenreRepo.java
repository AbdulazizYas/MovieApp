package co.jeel.movieapp.repositories;

import co.jeel.movieapp.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenreRepo extends JpaRepository<Genre, Long> {
    Genre findGenreByName(String name);
}
