package it.epicode.finalproject.exception;

import it.epicode.finalproject.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomizedExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError notFoundExceptionHandler(NotFoundException e){
        ApiError error = new ApiError();
        error.setMessage(e.getMessage());
        error.setDateError(LocalDateTime.now());
        return error;
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError validationExceptionHandler(ValidationException e){
        ApiError error = new ApiError();
        error.setMessage(e.getMessage());
        error.setDateError(LocalDateTime.now());
        return error;
    }

    @ExceptionHandler(ImATeapotException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT) // 418
    public ApiError imATeapotHandler(ImATeapotException e) {
        ApiError error = new ApiError();
        error.setMessage(e.getMessage());
        error.setDateError(LocalDateTime.now());
        return error;
    }

}
