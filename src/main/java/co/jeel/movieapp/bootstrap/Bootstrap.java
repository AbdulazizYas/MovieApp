package co.jeel.movieapp.bootstrap;

import co.jeel.movieapp.entities.Genre;
import co.jeel.movieapp.entities.Movie;
import co.jeel.movieapp.entities.MovieDetails;
import co.jeel.movieapp.entities.Review;
import co.jeel.movieapp.repositories.GenreRepo;
import co.jeel.movieapp.repositories.MovieDetailsRepo;
import co.jeel.movieapp.repositories.MovieRepo;
import co.jeel.movieapp.repositories.ReviewRepo;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class Bootstrap implements CommandLineRunner {

    private final GenreRepo genreRepo;
    private final MovieRepo movieRepo;
    private final ReviewRepo reviewRepo;
    private final MovieDetailsRepo movieDetailsRepo;

    @Override
    public void run(String... args) throws Exception {

        // ------------ Creating Genres
        Genre action = new Genre();
        action.setName("Action");

        Genre drama = new Genre();
        drama.setName("Drama");

        Genre horror = new Genre();
        horror.setName("Horror");

        genreRepo.save(action);
        genreRepo.save(drama);
        genreRepo.save(horror);


        // ----------- Creating Movies

        Movie quitePlace = createMovie(
                "A Quite Place 2018",
                "A family struggles for survival in a world where most humans have been killed by blind but noise-sensitive creatures. They are forced to communicate in sign language to keep the creatures at bay.",
                LocalDate.of(2018, Month.FEBRUARY,8),
                121,
                Arrays.asList(new String[]{"Action", "Horror"}));

        Movie y1917 = createMovie(
                "1917",
                "April 6th, 1917. As an infantry battalion assembles to wage war deep in enemy territory, two soldiers are assigned to race against time and deliver a message that will stop 1,600 men from walking straight into a deadly trap.",
                LocalDate.of(2019, Month.FEBRUARY,8),
                142,
                Arrays.asList(new String[]{"Action", "Drama"}));

        Movie lastSamurai = createMovie(
                "The Last Samurai 2003",
                "An American military advisor embraces the Samurai culture he was hired to destroy after he is captured in battle.",
                LocalDate.of(2003, Month.FEBRUARY,8),
                106,
                Arrays.asList(new String[]{"Action", "Drama"}));

        Movie vampireHunter = createMovie(
                "Abraham Lincoln: Vampire Hunter",
                "Abraham Lincoln, the 16th President of the United States, discovers vampires are planning to take over the United States. He makes it his mission to eliminate them.",
                LocalDate.of(2012, Month.FEBRUARY,16),
                105,
                Arrays.asList(new String[]{"Action", "Horror", "Drama"}));


        movieRepo.save(quitePlace);
        movieRepo.save(y1917);
        movieRepo.save(lastSamurai);
        movieRepo.save(vampireHunter);


        generateReviews(23);
    }


    private Movie createMovie(String title,
                             String desc,
                             LocalDate date,
                             Integer duration,
                             List<String> genres){

        Movie movie = new Movie();
        MovieDetails movieDetails = new MovieDetails();
        movie.setGenres(new ArrayList<Genre>());

        movieDetails.setDuration(duration);
        movieDetails.setRelease_date(date);
        movieDetails.setDesc(desc);

        movie.setTitle(title);
        List<Genre> genresList = new ArrayList<Genre>();
        genres.forEach(genre -> genresList.add(genreRepo.findGenreByName(genre)));

        movie.setGenres(genresList);
        movie.setMovieDetails(movieDetails);
        movieDetails.setMovie(movie);

        return movie;

    }

    private Review reviewMovie(Movie movie,String reviewer, Integer rating, String feedback){
        Review review = new Review();
        review.setReviewer(reviewer);
        review.setRating(rating);
        review.setFeedback(feedback);
        review.setMovie(movie);

        reviewRepo.save(review);
        return review;
    }

    private List<Review> generateReviews(int count){
        List<Review> reviews = new ArrayList<Review>();
        Faker faker = new Faker();
        List<Movie> movies = movieRepo.findAll();


        for (int i = 0; i < count; i++){
            reviews.add(reviewMovie(
                    movies.get(faker.random().nextInt(movies.size())),
                    faker.name().name(),
                    faker.random().nextInt(10),
                    faker.lorem().sentence(15)));
        }
        return reviews;
    }
}
