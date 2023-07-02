package co.jeel.movieapp.mappers;

import java.util.List;

import co.jeel.movieapp.DTOs.Movie.GetMovieDto;
import co.jeel.movieapp.DTOs.Movie.MovieListDto;
import co.jeel.movieapp.DTOs.Movie.UpdateMovieDto;
import co.jeel.movieapp.DTOs.MovieDetailsDto;
import co.jeel.movieapp.entities.MovieDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.jeel.movieapp.DTOs.Movie.CreateMovieDto;
import co.jeel.movieapp.entities.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {

  @Mapping(target = "id", source = "movie.id")
  GetMovieDto toMovieDto(Movie movie, MovieDetails movieDetails);



  @Mapping(target = "id", ignore = true)
  @Mapping(target = "genres", ignore = true)
  Movie toMovie(CreateMovieDto movieDto);

  @Mapping(target = "rating", ignore = true)
  MovieDetails toMovieDetails(MovieDetailsDto movieDetailsDto);

  @Mapping(target = "movieDetails.rating", ignore = true)
  void updateMovieFromDto(UpdateMovieDto dto, @MappingTarget Movie movie);


  List<MovieListDto> toMovieListDto(List<Movie> movies);


}
