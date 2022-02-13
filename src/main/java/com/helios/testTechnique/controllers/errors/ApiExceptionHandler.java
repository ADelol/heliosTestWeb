package com.helios.testTechnique.controllers.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.AdviceTrait;

/**
 * Controller advice to catch exception thrown by controllers in order to provide json response
 */
@ControllerAdvice
public class ApiExceptionHandler implements AdviceTrait {

    @ExceptionHandler(BadRequestException.class)
        public ResponseEntity<Problem> ProblemhandleBadRequestException(
                BadRequestException ex, NativeWebRequest request) {
        return create(Status.BAD_REQUEST, ex, request);
    }

}
