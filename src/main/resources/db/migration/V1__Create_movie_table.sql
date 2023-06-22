CREATE TABLE movie (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL
);

CREATE TABLE movie_details (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               description VARCHAR(255),
                               release_date DATE,
                               duration INT,
                               rating Decimal(10,2) DEFAULT 0.0,
                               movie_id BIGINT,
                               FOREIGN KEY (movie_id) REFERENCES movie (id)
);

CREATE TABLE genre (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255)
);

CREATE TABLE movie_genre (
                             movie_id BIGINT,
                             genre_id BIGINT,
                             PRIMARY KEY (movie_id, genre_id),
                             FOREIGN KEY (movie_id) REFERENCES movie (id),
                             FOREIGN KEY (genre_id) REFERENCES genre (id)
);

CREATE TABLE review (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        reviewer VARCHAR(255),
                        feedback VARCHAR(255),
                        rating BIGINT,
                        movie_id BIGINT,
                        FOREIGN KEY (movie_id) REFERENCES movie (id)
);