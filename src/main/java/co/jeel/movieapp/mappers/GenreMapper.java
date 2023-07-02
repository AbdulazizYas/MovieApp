package co.jeel.movieapp.mappers;

import co.jeel.movieapp.DTOs.GenreDto;
import co.jeel.movieapp.DTOs.ReviewDto;
import co.jeel.movieapp.entities.Genre;
import co.jeel.movieapp.entities.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre toGenre(GenreDto genreDto);

    GenreDto toGenreDto(Genre genre);

    List<GenreDto> toGenreDtoList(List<Genre> genres);

    List<Genre> toGenreList(List<GenreDto> genres);
}
