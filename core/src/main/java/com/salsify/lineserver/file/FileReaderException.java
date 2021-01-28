package com.salsify.lineserver.file;

public class FileReaderException extends Exception {
    public FileReaderException() { }

    public FileReaderException(String message) {
        super(message);
    }

    public FileReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
