package com.salsify.lineserver;

public class LineReaderException extends Exception {
    public LineReaderException(String message) {
        super(message);
    }

    public LineReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
