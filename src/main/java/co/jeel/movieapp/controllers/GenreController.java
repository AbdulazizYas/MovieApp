package co.jeel.movieapp.controllers;

import co.jeel.movieapp.DTOs.GenreDto;
import co.jeel.movieapp.DTOs.MovieDto;
import co.jeel.movieapp.DTOs.ReviewDto;
import co.jeel.movieapp.services.GenreService;
import co.jeel.movieapp.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
class GenreController {


    private final GenreService genreService;

    @GetMapping
    public List<GenreDto> getAll() {
        return this.genreService.getAllGenres();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenreDto addGenre(@RequestBody GenreDto genreDto){
        return this.genreService.addGenre(genreDto);
    }
    @GetMapping("{genreName}")
    public List<MovieDto> getMoviesByGenre(@PathVariable String genreName){
        return this.genreService.getGenreMoviesByName(genreName);
    }
}