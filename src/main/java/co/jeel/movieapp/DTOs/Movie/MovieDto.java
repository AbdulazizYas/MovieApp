package co.jeel.movieapp.DTOs.Movie;


import co.jeel.movieapp.DTOs.GenreDto;
import co.jeel.movieapp.DTOs.MovieDetailsDto;
import co.jeel.movieapp.DTOs.ReviewDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class MovieDto {

  private Long id;

  @NotEmpty
  private String title;

  @NotNull
  private MovieDetailsDto movieDetails;

  private List<GenreDto> genres;

  private List<ReviewDto> reviews;

}
