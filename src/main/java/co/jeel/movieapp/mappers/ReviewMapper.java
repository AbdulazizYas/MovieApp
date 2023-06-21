package co.jeel.movieapp.mappers;

import co.jeel.movieapp.DTOs.ReviewDto;
import co.jeel.movieapp.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {


    Review toReview(ReviewDto reviewDto);

    ReviewDto toReviewDto(Review review);

    List<ReviewDto> toReviewDtoList(List<Review> reviews);
}
