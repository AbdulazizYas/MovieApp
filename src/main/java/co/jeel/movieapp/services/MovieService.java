package co.jeel.movieapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import co.jeel.movieapp.exceptions.NotFoundException;
import co.jeel.movieapp.DTOs.MovieDTO;
import co.jeel.movieapp.entities.Movie;
import co.jeel.movieapp.mappers.MovieMapper;
import co.jeel.movieapp.repositories.MovieRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieService {
  
  private final MovieRepo movieRepo;
  private final MovieMapper movieMapper;

  public List<MovieDTO> getAllMovies(){

    return movieMapper.toMovieDtoList(movieRepo.findAll());
  }

  public MovieDTO getMovieById(Long id){
    return movieMapper.toMovieDto(
      movieRepo.findById(id).orElseThrow(() -> new NotFoundException("Movie not found with id: " + id))
    );
  }

  public MovieDTO createMovie(MovieDTO movieDTO) {
    log.info("Creating a new movie with ID: ");

    Movie movie = movieMapper.toMovie(movieDTO);

    Movie savedMovie = movieRepo.save(movie);

    return movieMapper.toMovieDto(savedMovie);
  }
  
  
  public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
      Movie movie = movieRepo.findById(id).orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));

      movieMapper.updateMovieFromDto(movieDTO,movie);
      Movie updatedMovie = movieRepo.save(movie);

      return movieMapper.toMovieDto(updatedMovie);
  }
  
  
  public void deleteMovie(Long id) {
    if (movieRepo.existsById(id)) {
        movieRepo.deleteById(id);
    } else {
        throw new NotFoundException("Movie not found with id: " + id);
    }
}
}
