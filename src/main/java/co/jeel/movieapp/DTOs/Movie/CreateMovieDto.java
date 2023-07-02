package co.jeel.movieapp.DTOs.Movie;


import co.jeel.movieapp.DTOs.GenreDto;
import co.jeel.movieapp.DTOs.MovieDetailsDto;
import co.jeel.movieapp.DTOs.ReviewDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class CreateMovieDto {


  @NotEmpty
  private String title;

  @NotNull
  private MovieDetailsDto movieDetails;

  @NotNull
  private List<GenreDto> genres;


}
