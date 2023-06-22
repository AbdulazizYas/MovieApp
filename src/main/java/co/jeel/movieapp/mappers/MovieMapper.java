package co.jeel.movieapp.mappers;

import java.util.List;

import co.jeel.movieapp.DTOs.Movie.MovieListDto;
import co.jeel.movieapp.DTOs.MovieDetailsDto;
import co.jeel.movieapp.entities.MovieDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.jeel.movieapp.DTOs.Movie.MovieDto;
import co.jeel.movieapp.entities.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {

  @Mapping(target = "id", source = "movie.id")
  MovieDto toMovieDto(Movie movie, MovieDetails movieDetails);



  @Mapping(target = "id", ignore = true)
  Movie toMovie(MovieDto movieDto);

  @Mapping(target = "rating", ignore = true)
  MovieDetails toMovieDetails(MovieDetailsDto movieDetailsDto);

  default void updateMovieFromDto(MovieDto dto, @MappingTarget Movie movie){
    if ( dto != null ) {
      movie.setTitle( dto.getTitle() );
      MovieDetailsDto details =  dto.getMovieDetails() ;
      movie.getMovieDetails().setDuration(details.getDuration());
      movie.getMovieDetails().setDescription(details.getDesc());
      movie.getMovieDetails().setRating(details.getRating());
      movie.getMovieDetails().setRelease_date(details.getRelease_date());
    }
  }


  List<MovieListDto> toMovieListDto(List<Movie> movies);


}
