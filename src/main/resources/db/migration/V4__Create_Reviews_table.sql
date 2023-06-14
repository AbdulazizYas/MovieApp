CREATE TABLE review (
                        id INT PRIMARY KEY,
                        reviewer VARCHAR(255),
                        reviewDesc VARCHAR(255),
                        rating INT,
                        movie_id INT,
                        FOREIGN KEY (movie_id) REFERENCES movie (id)
);