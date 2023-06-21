package co.jeel.movieapp.DTOs.Movie;

import co.jeel.movieapp.DTOs.MovieDetailsDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class MovieListDto {
    private Long id;

    @NotEmpty
    private String title;

    @NotNull
    private MovieDetailsDto movieDetails;
}
