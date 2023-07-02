package co.jeel.movieapp.services;

import co.jeel.movieapp.DTOs.Movie.CreateMovieDto;
import co.jeel.movieapp.DTOs.Movie.GetMovieDto;
import co.jeel.movieapp.DTOs.ReviewDto;
import co.jeel.movieapp.entities.Movie;
import co.jeel.movieapp.entities.Review;
import co.jeel.movieapp.exceptions.NotFoundException;
import co.jeel.movieapp.mappers.MovieMapper;
import co.jeel.movieapp.mappers.ReviewMapper;
import co.jeel.movieapp.repositories.MovieRepo;
import co.jeel.movieapp.repositories.ReviewRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {

  private final MovieRepo movieRepo;
  private final ReviewRepo reviewRepo;

  private final ReviewMapper reviewMapper;
  private final MovieMapper movieMapper;

  public List<ReviewDto> getAllReviews(){

    return reviewMapper.toReviewDtoList(reviewRepo.findAll());
  }

  public GetMovieDto getMovieByReview(Long reviewId){
    Review review = reviewRepo.findById(reviewId).orElseThrow(() -> new NotFoundException("Movie not found with id: " + reviewId));
    Movie movie = movieRepo.findMovieByReviewsContains(review);
    return movieMapper.toMovieDto(movie, movie.getMovieDetails());

  }


}
