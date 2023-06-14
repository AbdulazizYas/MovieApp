package co.jeel.movieapp.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ReviewDto {

    private String reviewer;
    private String reviewDesc;
    private int rating;

    private Long movie_id;

}