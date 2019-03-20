package com.dexmatech.handler;

import com.dexmatech.domain.exception.GeoApiException;
import com.dexmatech.domain.exception.BadRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Add a Global Exception handler to ensure there's no any Exception going out our platform.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{

    private static final String BAD_REQUEST = "Oops ! There's an error in your params, please have it a look and try again.";
    private static final String DEFAULT_ERROR_MSG = "Oops ! There's a fatal error in our platform. Try again later.";

    /*
     * Handles unchecked and therefore critical exceptions
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> defaultErrorHandler(final WebRequest request, final Exception e) throws Exception
    {
        /*
         * If Exception is MyTaxiExternalException is allowed to show it to the end-user.
         * ---> The criteria of the test requests to throw some exceptions to the end-user.
         */
        if (e instanceof GeoApiException) {
            throw e;
        }
        return handleExceptionInternal(e, DEFAULT_ERROR_MSG, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    /*
     * Handles Bad requests from user.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        return handleExceptionInternal(new BadRequest(), BAD_REQUEST, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
