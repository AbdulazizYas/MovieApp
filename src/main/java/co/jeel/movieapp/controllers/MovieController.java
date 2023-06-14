package co.jeel.movieapp.controllers;

import java.util.List;

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

import co.jeel.movieapp.DTOs.MovieDto;
import co.jeel.movieapp.services.MovieService;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
class MovieController {


    private final MovieService movieService;

    @GetMapping
    public List<MovieDto> getAll() {
        return this.movieService.getAllMovies();
    }

    @GetMapping("{id}")
    public MovieDto getById(@PathVariable("id") Long id) {
        return this.movieService.getMovieById(id);
    }

    @PostMapping
    public MovieDto create(@RequestBody MovieDto movie) {
            return this.movieService.createMovie(movie);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto update(@PathVariable("id") Long id, @RequestBody MovieDto movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {

        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    }
}