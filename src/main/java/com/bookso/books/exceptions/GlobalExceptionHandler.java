package com.bookso.books.exceptions;

import com.bookso.books.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bookso.books.constatns.BooksConstants.*;

/*
* @author: santhosh kumar
* @description: Exception handler of BooksException
 */
@ControllerAdvice
public class GlobalExceptionHandler<T extends RuntimeException> {

    /*
    * @author: santhosh
    * @description: Method to handle Validation related Exceptions
    * @params: MethodArgumentNotValidException
    * @returns: Error Details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentExceptions(MethodArgumentNotValidException me){
        List<FieldError> fieldErrorList = me.getBindingResult().getFieldErrors();
        Map<String , String> errors =fieldErrorList
                .stream()
                .collect(Collectors.toMap(FieldError::getField, x->x.getDefaultMessage())); //Converting Field Errors to readable Errors

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /*
     * @author: santhosh
     * @description: Method to handle Books Exceptions and OrdersException
     * @params: BooksException and WebRequest
     * @returns: Error Details
     */
    @ExceptionHandler(BooksoException.class)
    public ResponseEntity<ErrorDto> handleBooksException( BooksoException be , WebRequest request){

       ErrorDto error = ErrorDto.builder()
               .code(be.getError().toString())
               .message(be.getMessage())
               .path(request.getDescription(false))
               .timeStamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(error);

    }
}
