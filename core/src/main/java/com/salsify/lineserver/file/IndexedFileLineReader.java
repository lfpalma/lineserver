package com.salsify.lineserver.file;

import com.salsify.lineserver.LineReader;

public class IndexedFileLineReader implements LineReader {
    private FileLineIndex<FileLineIndexEntry> fileLineIndex;
    private FileReader fileReader;

    @Override
    public String readLine(long index) {
        return null;
    }
}
