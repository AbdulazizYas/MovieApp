CREATE TABLE movie_details (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               desc VARCHAR(255),
                               release_date DATE,
                               duration INT,
                               rating Decimal(10,2) DEFAULT 0.0,
                               movie_id BIGINT,
                               FOREIGN KEY (movie_id) REFERENCES movie (id)
);