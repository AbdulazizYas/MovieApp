package co.jeel.movieapp.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ReviewDto {

    private String reviewer;
    private String feedback;

    @Min(0)
    @Max(10)
    private int rating;


}