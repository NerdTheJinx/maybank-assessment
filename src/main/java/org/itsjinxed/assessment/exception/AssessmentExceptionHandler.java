package org.itsjinxed.assessment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@Slf4j
@RestControllerAdvice
public class AssessmentExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception exception) {
        log.error("Unhandled exception caught: {}", exception.getMessage(), exception);
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "The error occurred in the service, try again later.");
        problem.setTitle("Internal Server Error");
        problem.setType(URI.create("http://example.com/errors/generic"));
        return problem;
    }

    @ExceptionHandler(DataValidationException.class)
    public ProblemDetail handleDataValidation(DataValidationException exception) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        problem.setTitle("Validation Error");
        problem.setType(URI.create("http://example.com/errors/data-validation"));
        return problem;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgument(IllegalArgumentException exception) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        problem.setTitle("Invalid Input Error");
        problem.setType(URI.create("http://example.com/errors/input-format"));
        return problem;
    }
}
