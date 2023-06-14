package co.jeel.movieapp.mappers;

import java.util.List;

import co.jeel.movieapp.DTOs.GetMovieDto;
import co.jeel.movieapp.DTOs.MovieDetailsDto;
import co.jeel.movieapp.entities.MovieDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import co.jeel.movieapp.DTOs.MovieDto;
import co.jeel.movieapp.entities.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {

  MovieDto toMovieDto(Movie movie);

  GetMovieDto toGetMovieDto(Movie movie);

  Movie toMovie(MovieDto movieDto);

  MovieDetails toMovieDetails(MovieDetailsDto movieDetailsDto);



  void updateMovieFromDto(MovieDto dto, @MappingTarget Movie movie);

  List<MovieDto> toMovieDtoList(List<Movie> movies);
}
