package co.jeel.movieapp.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{


  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource was not found")
  public NotFoundException handleNotFoundException(
     NotFoundException exception, 
      WebRequest request
  ){
    return exception;
  }

  @ExceptionHandler(GenreNotExistException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Genre you specified does not exist")
  public GenreNotExistException handleGenreNotExistException(
          GenreNotExistException exception,
          WebRequest request
  ){
    return exception;
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request) {

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) ->{

      String fieldName = ((FieldError) error).getField();
      String message = error.getDefaultMessage();
      errors.put(fieldName, message);
    });
    return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
  }
  private ResponseEntity<ApiError> buildResponseError(ApiError error){
    return new ResponseEntity<ApiError>(error, error.getStatus());
  }
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    // TODO Auto-generated method stub
    return super.handleExceptionInternal(ex, body, headers, status, request);
  }


}
