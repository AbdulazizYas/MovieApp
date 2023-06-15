package co.jeel.movieapp.services;

import java.util.List;

import co.jeel.movieapp.DTOs.GenreDto;
import co.jeel.movieapp.DTOs.ReviewDto;
import co.jeel.movieapp.entities.Genre;
import co.jeel.movieapp.entities.MovieDetails;
import co.jeel.movieapp.entities.Review;
import co.jeel.movieapp.mappers.GenreMapper;
import co.jeel.movieapp.mappers.ReviewMapper;
import co.jeel.movieapp.repositories.GenreRepo;
import co.jeel.movieapp.repositories.MovieDetailsRepo;
import co.jeel.movieapp.repositories.ReviewRepo;
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
  private final GenreRepo genreRepo;

  private final MovieMapper movieMapper;
  private final ReviewMapper reviewMapper;
  private final GenreMapper genreMapper;

  public List<MovieDto> getAllMovies(){

    return movieMapper.toMovieDtoList(movieRepo.findAll());
  }

  public MovieDto getMovieById(Long id){
    Movie movie = movieRepo.findById(id).orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));

    return movieMapper.toMovieDto(movie,movie.getMovieDetails());
  }

  public MovieDto createMovie(MovieDto movieDTO) {
    log.info("Creating a new movie with ID: ");

    Movie movie = movieMapper.toMovie(movieDTO);
    MovieDetails details = movieMapper.toMovieDetails(movieDTO.getMovieDetails());

    movie.setMovieDetails(details);
    details.setMovie(movie);

    Movie savedMovie = movieRepo.save(movie);

    return movieMapper.toMovieDto(savedMovie, savedMovie.getMovieDetails());
  }
  
  
  public MovieDto updateMovie(Long id, MovieDto movieDTO) {
      Movie movie = movieRepo.findById(id).orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));

      movieMapper.updateMovieFromDto(movieDTO,movie);
      Movie updatedMovie = movieRepo.save(movie);

      return movieMapper.toMovieDto(updatedMovie, updatedMovie.getMovieDetails());
  }
  
  
  public void deleteMovie(Long id) {
    if (movieRepo.existsById(id)) {
        movieRepo.deleteById(id);
    } else {
        throw new NotFoundException("Movie not found with id: " + id);
    }
}

  public void reviewMovie(Long id, ReviewDto reviewDto){
    Movie movie = movieRepo.findById(id).orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));

    Review review = reviewMapper.toReview(reviewDto);

    movie.getReviews().add(review);
    review.setMovie(movie);

    movieRepo.save(movie);

  }

  public List<ReviewDto> getReviewsByMovie(Long id){
    Movie movie = movieRepo.findById(id).orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));


    return reviewMapper.toReviewDtoList(movie.getReviews());
  }

  public List<GenreDto> getMovieGenres(Long movieId){
    Movie movie = movieRepo.findById(movieId).orElseThrow(() -> new NotFoundException("Movie not found with id: " + movieId));

    return  genreMapper.toGenreDtoList(movie.getGenres());
  }

  public void addGenreToMovie(String genreName, Long movieId){
    Movie movie = movieRepo.findById(movieId).orElseThrow(() -> new NotFoundException("Movie not found with id: " + movieId));

    Genre genre = genreRepo.findGenreByName(genreName);

    if (genre == null){
      throw new NotFoundException("Movie not found with name: " + genreName);
    }

    movie.getGenres().add(genre);
    genre.getMovies().add(movie);

    movieRepo.save(movie);
  }

}
