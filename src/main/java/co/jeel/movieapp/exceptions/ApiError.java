package co.jeel.movieapp.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError {

    private HttpStatus status;
    private String message;
    private String debugMessage;
    private LocalDateTime timestamp;

    private ApiError(){
        timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus status){
        this();
        this.status = status;
    }
    ApiError(HttpStatus status, Throwable ex){
        this();
        this.status = status;
        this.message = "Unexpected Error";
        this.debugMessage = ex.getLocalizedMessage();
    }
    ApiError(HttpStatus status,String message, Throwable ex){
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }



}
