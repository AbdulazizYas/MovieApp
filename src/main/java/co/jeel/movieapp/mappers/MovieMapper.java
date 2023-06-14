package co.jeel.movieapp.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import co.jeel.movieapp.DTOs.MovieDTO;
import co.jeel.movieapp.entities.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {

  MovieDTO toMovieDto(Movie movie);
  Movie toMovie(MovieDTO movieDto);
  void updateMovieFromDto(MovieDTO dto, @MappingTarget Movie movie);
  List<MovieDTO> toMovieDtoList(List<Movie> movies);
}
