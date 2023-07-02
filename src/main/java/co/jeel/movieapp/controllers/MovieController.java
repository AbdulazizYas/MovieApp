package co.jeel.movieapp.controllers;

import java.util.List;

import co.jeel.movieapp.DTOs.GenreDto;
import co.jeel.movieapp.DTOs.Movie.GetMovieDto;
import co.jeel.movieapp.DTOs.Movie.MovieListDto;
import co.jeel.movieapp.DTOs.Movie.UpdateMovieDto;
import co.jeel.movieapp.DTOs.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.jeel.movieapp.DTOs.Movie.CreateMovieDto;
import co.jeel.movieapp.services.MovieService;

import javax.validation.Valid;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
class MovieController {


    private final MovieService movieService;

    @GetMapping
    public List<MovieListDto> getAll() {
        return this.movieService.getAllMovies();
    }

    @GetMapping("{id}")
    public GetMovieDto getById(@PathVariable("id") Long id) {
        return this.movieService.getMovieById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GetMovieDto create(@Valid @RequestBody CreateMovieDto movie) {
            return this.movieService.createMovie(movie);
    }

    @PostMapping("{id}/review")
    public ResponseEntity<HttpStatus> reviewMovie(@PathVariable Long id, @Valid @RequestBody ReviewDto reviewDto) {
        this.movieService.reviewMovie(id, reviewDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping("{id}/genres/{genreName}")
    public ResponseEntity<HttpStatus> addGenreToMovie(@PathVariable Long id, @PathVariable String genreName) {
        this.movieService.addGenreToMovie(genreName, id);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("{id}/reviews")
    public List<ReviewDto> reviewMovie(@PathVariable Long id) {
        return this.movieService.getReviewsByMovie(id);

    }

    @GetMapping("{id}/genres")
    public List<GenreDto> getMovieGenres(@PathVariable Long id) {
        return this.movieService.getMovieGenres(id);

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public GetMovieDto update(@PathVariable("id") Long id, @RequestBody UpdateMovieDto movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {

        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    }
}