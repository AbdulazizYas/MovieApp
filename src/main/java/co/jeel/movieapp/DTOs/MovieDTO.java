package co.jeel.movieapp.DTOs;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieDTO {

  private String name;

  private Double rating = 0.0;
}
