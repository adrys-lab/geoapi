package com.dexmatech.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST,
		reason = "Geo api detected bad request, try again with correct params.")
public class BadRequest extends Exception {
}