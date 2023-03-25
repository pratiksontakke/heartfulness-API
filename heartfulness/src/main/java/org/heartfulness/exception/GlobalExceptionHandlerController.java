package org.heartfulness.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

  @Bean
  public ErrorAttributes errorAttributes() {
    // Hide exception field in the return object
    return new DefaultErrorAttributes() {
      @Override
      public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        return super.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults().excluding(ErrorAttributeOptions.Include.EXCEPTION));
      }
    };
  }

  @ExceptionHandler(CustomException.class)
  public void handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
    res.sendError(ex.getHttpStatus().value(), ex.getMessage());
  }

  @ExceptionHandler(AccessDeniedException.class)
  public void handleAccessDeniedException(HttpServletResponse res) throws IOException {
    res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
  }

  @ExceptionHandler(Exception.class)
  public void handleException(HttpServletResponse res) throws IOException {
    res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong");
  }

  // Our custom exceptions
  @ExceptionHandler(VisitorException.class)
  public ResponseEntity<MyErrorDetails> loginException(VisitorException ve, WebRequest req) {
    MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(), ve.getMessage(), req.getDescription(false));
    return new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(VisitorLogException.class)
  public ResponseEntity<MyErrorDetails> loginException(VisitorLogException vle, WebRequest req) {
    MyErrorDetails myErrorDetails = new MyErrorDetails(LocalDateTime.now(), vle.getMessage(), req.getDescription(false));
    return new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);
  }

}
