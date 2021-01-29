package com.salsify.web.services.lines;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class LinesServiceException extends RuntimeException {

    @ResponseStatus(code= HttpStatus.PAYLOAD_TOO_LARGE)
    public static class LineNotFoundException extends LinesServiceException { }
}
