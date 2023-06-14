package co.jeel.movieapp.services;

import java.util.List;

import co.jeel.movieapp.entities.MovieDetails;
import co.jeel.movieapp.repositories.MovieDetailsRepo;
import org.springframework.stereotype.Service;

import co.jeel.movieapp.exceptions.NotFoundException;
import co.jeel.movieapp.DTOs.MovieDto;
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
  private final MovieDetailsRepo movieDetailsRepo;
  private final MovieMapper movieMapper;

  public List<MovieDto> getAllMovies(){

    return movieMapper.toMovieDtoList(movieRepo.findAll());
  }

  public MovieDto getMovieById(Long id){
    return movieMapper.toMovieDto(
      movieRepo.findById(id).orElseThrow(() -> new NotFoundException("Movie not found with id: " + id))
    );
  }

  public MovieDto createMovie(MovieDto movieDTO) {
    log.info("Creating a new movie with ID: ");

    Movie movie = movieMapper.toMovie(movieDTO);
    MovieDetails details = movieMapper.toMovieDetails(movieDTO.getDetails());

    movie.setMovieDetails(details);
    details.setMovie(movie);

    Movie savedMovie = movieRepo.save(movie);
    MovieDetails savedMovieDetails = movieDetailsRepo.save(details);

    return movieMapper.toMovieDto(savedMovie);
  }
  
  
  public MovieDto updateMovie(Long id, MovieDto movieDTO) {
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
