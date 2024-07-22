package microstamp.step1.configuration;

import microstamp.step1.exception.Step1Error;
import microstamp.step1.exception.Step1ErrorResponse;
import microstamp.step1.exception.Step1NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Step1NotFoundException.class })
    protected ResponseEntity<Object> handleStep1NotFound(Step1NotFoundException ex, WebRequest request) {
        Step1ErrorResponse errorResponse = new Step1ErrorResponse();
        errorResponse.addError(new Step1Error(ex.getClass().getSimpleName(),"NotFound",ex.getMessage()));
        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
