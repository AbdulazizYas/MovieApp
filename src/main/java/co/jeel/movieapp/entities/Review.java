package co.jeel.movieapp.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewer;
    private String reviewDesc;
    private int rating;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id", nullable = false, referencedColumnName = "id")
    private Movie movie;

}