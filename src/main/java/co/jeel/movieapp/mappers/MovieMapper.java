package co.jeel.movieapp.mappers;

import java.util.List;

import co.jeel.movieapp.DTOs.MovieDetailsDto;
import co.jeel.movieapp.entities.MovieDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import co.jeel.movieapp.DTOs.MovieDto;
import co.jeel.movieapp.entities.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {

  MovieDto toMovieDto(Movie movie, MovieDetails movieDetails);

  Movie toMovie(MovieDto movieDto);

  MovieDetails toMovieDetails(MovieDetailsDto movieDetailsDto);

  default void updateMovieFromDto(MovieDto dto, @MappingTarget Movie movie){
    if ( dto != null ) {
      movie.setTitle( dto.getTitle() );
      MovieDetails details = toMovieDetails( dto.getMovieDetails() );
      movie.getMovieDetails().setDuration(details.getDuration());
      movie.getMovieDetails().setDesc(details.getDesc());
      movie.getMovieDetails().setRating(details.getRating());
      movie.getMovieDetails().setRelease_date(details.getRelease_date());
    }
  }

  List<MovieDto> toMovieDtoList(List<Movie> movies);
}
