package co.jeel.movieapp.exceptions;

public class GenreNotExistException extends RuntimeException {
    public GenreNotExistException(String message) {
        super(message);
    }
}
