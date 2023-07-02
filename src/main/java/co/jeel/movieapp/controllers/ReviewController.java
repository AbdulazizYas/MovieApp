package co.jeel.movieapp.controllers;

import co.jeel.movieapp.DTOs.Movie.CreateMovieDto;
import co.jeel.movieapp.DTOs.Movie.GetMovieDto;
import co.jeel.movieapp.DTOs.ReviewDto;
import co.jeel.movieapp.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
class ReviewController {


    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDto> getAll() {
        return this.reviewService.getAllReviews();
    }

    @GetMapping("{id}")
    public GetMovieDto getMovieByReview(@PathVariable Long id){
        return this.reviewService.getMovieByReview(id);
    }
}