package project.PromotionInformationAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.PromotionInformationAPI.domain.dto.Result;
import project.PromotionInformationAPI.exception.Exception.*;

@RestControllerAdvice
public class ErrorHandlers {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        ErrorResponse error = new ErrorResponse(Result.FAIL,e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleServersException(Exception e) {
        ErrorResponse error = new ErrorResponse(Result.FAIL,HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ServerException.class})
    public ResponseEntity<ErrorResponse> handleServerException(ServerException e) {
        ErrorResponse error = new ErrorResponse(Result.FAIL,e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({SecessionException.class})
    public ResponseEntity<ErrorResponse> handleSecessionException(SecessionException e) {
        ErrorResponse error = new ErrorResponse(Result.FAIL,e.getErrorCode().getStatus(), e.getErrorCode().getMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse error = new ErrorResponse(Result.FAIL,HttpStatus.BAD_REQUEST.value(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidRequestException.class})
    public ResponseEntity<ErrorResponse> handleInValidRequestException(InvalidRequestException e) {
        ErrorResponse error = new ErrorResponse(Result.FAIL,HttpStatus.BAD_REQUEST.value(), e.getErrorCode().getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AlreadySavedException.class})
    public ResponseEntity<ErrorResponse> handleInValidRequestException(AlreadySavedException e) {
        ErrorResponse error = new ErrorResponse(Result.FAIL,HttpStatus.CONFLICT.value(), e.getErrorCode().getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
