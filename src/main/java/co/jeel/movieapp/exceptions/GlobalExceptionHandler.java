package co.jeel.movieapp.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


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
