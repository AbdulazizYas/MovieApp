package co.jeel.movieapp.DTOs;


import co.jeel.movieapp.entities.MovieDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetMovieDto {

  private String title;

  private MovieDetails movieDetails;

}
