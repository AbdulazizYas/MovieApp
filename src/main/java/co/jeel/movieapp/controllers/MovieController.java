package co.jeel.movieapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import co.jeel.movieapp.DTOs.MovieDTO;
import co.jeel.movieapp.services.MovieService;


@RestController
@RequestMapping("/movies")
class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping
    public List<MovieDTO> getAll() {
        return this.movieService.getAllMovies();
    }

    @GetMapping("{id}")
    public MovieDTO getById(@PathVariable("id") Long id) {
        return this.movieService.getMovieById(id);
    }

    @PostMapping
    public MovieDTO create(@RequestBody MovieDTO movie) {
            return this.movieService.createMovie(movie);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDTO update(@PathVariable("id") Long id, @RequestBody MovieDTO movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {

        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    }
}