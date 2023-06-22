package co.jeel.movieapp.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class MovieDetailsDto {

    private String description;
    private LocalDate release_date;
    private Integer duration;
    private Double rating = 0.0;

}