package com.salsify.lineserver.file;

public class FileLineIndexException extends Exception {
    public FileLineIndexException() { }

    public FileLineIndexException(String message) {
        super(message);
    }

    public FileLineIndexException(String message, Throwable cause) {
        super(message, cause);
    }
}
