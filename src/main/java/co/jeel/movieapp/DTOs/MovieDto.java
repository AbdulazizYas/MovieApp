package co.jeel.movieapp.DTOs;


import co.jeel.movieapp.entities.MovieDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class MovieDto {

  private String title;

  private MovieDetailsDto movieDetails;

}
