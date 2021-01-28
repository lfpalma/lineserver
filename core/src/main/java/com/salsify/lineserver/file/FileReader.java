package com.salsify.lineserver.file;

import java.io.Closeable;
import java.io.IOException;

public abstract class FileReader implements Closeable {

    protected FileReader() { }

    public String readLine()
            throws FileReaderException {

        StringBuilder result = new StringBuilder();

        Character c = read();
        while(isNotEndOfLine(c) && isNotEndOfFile(c)) {
            result.append(c);
            c = read();
        }

        return result.toString();
    }

    private boolean isNotEndOfLine(Character c) {
        return c != '\n';
    }

    private boolean isNotEndOfFile(Character c) {
        return c != null;
    }

    public abstract long getFilePointer()
            throws FileReaderException;

    public abstract Character read()
            throws FileReaderException;

    public abstract String read(long startPosition, long endPosition)
            throws FileReaderException;

    public abstract void close()
            throws IOException;
}
