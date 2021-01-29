package com.salsify.lineserver;

import com.salsify.lineserver.file.FileReaderException;

public interface LineReader {
    String readLine(long index) throws FileReaderException;
}
