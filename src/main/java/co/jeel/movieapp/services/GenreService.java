package co.jeel.movieapp.services;

import co.jeel.movieapp.DTOs.GenreDto;
import co.jeel.movieapp.DTOs.MovieDto;
import co.jeel.movieapp.DTOs.ReviewDto;
import co.jeel.movieapp.entities.Genre;
import co.jeel.movieapp.entities.Movie;
import co.jeel.movieapp.entities.Review;
import co.jeel.movieapp.exceptions.NotFoundException;
import co.jeel.movieapp.mappers.GenreMapper;
import co.jeel.movieapp.mappers.MovieMapper;
import co.jeel.movieapp.mappers.ReviewMapper;
import co.jeel.movieapp.repositories.GenreRepo;
import co.jeel.movieapp.repositories.MovieRepo;
import co.jeel.movieapp.repositories.ReviewRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenreService {

  private final MovieRepo movieRepo;
  private final GenreRepo genreRepo;

  private final GenreMapper genreMapper;
  private final MovieMapper movieMapper;

  public List<GenreDto> getAllGenres(){
    return genreMapper.toGenreDtoList(genreRepo.findAll());
  }

  public GenreDto addGenre(GenreDto genreDto){
    Genre genre = genreMapper.toGenre(genreDto);

    return genreMapper.toGenreDto(genreRepo.save(genre));

  }

  public List<MovieDto> getGenreMoviesByName(String genreName){
    Genre genre = genreRepo.findGenreByName(genreName);

    if (genre == null){
      throw new NotFoundException("Movie not found with name: " + genreName);
    }
    return movieMapper.toMovieDtoList(genre.getMovies());

  }


}
