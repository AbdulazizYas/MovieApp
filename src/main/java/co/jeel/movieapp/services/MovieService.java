package co.jeel.movieapp.services;

import java.util.ArrayList;
import java.util.List;

import co.jeel.movieapp.DTOs.GenreDto;
import co.jeel.movieapp.DTOs.Movie.GetMovieDto;
import co.jeel.movieapp.DTOs.Movie.MovieListDto;
import co.jeel.movieapp.DTOs.Movie.UpdateMovieDto;
import co.jeel.movieapp.DTOs.ReviewDto;
import co.jeel.movieapp.entities.Genre;
import co.jeel.movieapp.entities.MovieDetails;
import co.jeel.movieapp.entities.Review;
import co.jeel.movieapp.exceptions.GenreNotExistException;
import co.jeel.movieapp.mappers.GenreMapper;
import co.jeel.movieapp.mappers.ReviewMapper;
import co.jeel.movieapp.repositories.GenreRepo;
import org.springframework.stereotype.Service;

import co.jeel.movieapp.exceptions.NotFoundException;
import co.jeel.movieapp.DTOs.Movie.CreateMovieDto;
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

  public List<MovieListDto> getAllMovies(){

    return movieMapper.toMovieListDto(movieRepo.findAll());
  }

  public GetMovieDto getMovieById(Long id){
    Movie movie = movieRepo.findById(id).orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));

    return movieMapper.toMovieDto(movie,movie.getMovieDetails());
  }

  public GetMovieDto createMovie(CreateMovieDto createMovieDTO) {

    createMovieDTO.getGenres().forEach(genreDto -> {
      if (genreRepo.findGenreByName(genreDto.getName()) == null){
        throw new GenreNotExistException("The genre with name: " + genreDto.getName());
      }
    });


    Movie movie = movieMapper.toMovie(createMovieDTO);
    MovieDetails details = movieMapper.toMovieDetails(createMovieDTO.getMovieDetails());

    movie.setMovieDetails(details);
    details.setMovie(movie);


    createMovieDTO.getGenres().forEach(genreDto -> {
      Genre genre = genreRepo.findGenreByName(genreDto.getName());
      genre.getMovies().add(movie);

      
      movie.getGenres().add(genre);
    });


    Movie savedMovie = movieRepo.save(movie);

    return movieMapper.toMovieDto(savedMovie, savedMovie.getMovieDetails());
  }
  
  
  public GetMovieDto updateMovie(Long id, UpdateMovieDto updateMovieDto) {
      Movie movie = movieRepo.findById(id).orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));



      movieMapper.updateMovieFromDto(updateMovieDto,movie);
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
      throw new GenreNotExistException("Genre not found with name: " + genreName);
    }

    movie.getGenres().add(genre);
    genre.getMovies().add(movie);

    movieRepo.save(movie);
  }

}
