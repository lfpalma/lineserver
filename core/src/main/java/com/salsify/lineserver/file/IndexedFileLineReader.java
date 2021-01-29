package com.salsify.lineserver.file;

import com.salsify.lineserver.LineReader;
import com.salsify.lineserver.LineReaderException;

import java.io.Serializable;

public class IndexedFileLineReader implements LineReader {
    private FileLineIndex<FileLineIndexEntry> fileLineIndex;
    private FileReader fileReader;

    public IndexedFileLineReader (
            FileLineIndex<FileLineIndexEntry> fileLineIndex, FileReader fileReader)
            throws LineReaderException {

        this.fileLineIndex = fileLineIndex;
        this.fileReader = fileReader;
        build();
    }

    private void build()
            throws LineReaderException {
        try {
            long startPosition = fileReader.getFilePointer();
            long endPosition;
            for(long line = 1; fileReader.readLine() != null; line++) {
                endPosition = fileReader.getFilePointer() - 1;
                fileLineIndex.put(line, new FileLineIndexEntryImpl(startPosition, endPosition));
                startPosition = endPosition + 1;
            }
            fileReader.rewind();
        }
        catch(Exception e) {
            throw new LineReaderException("Could not build file line index", e);
        }
    }

    @Override
    public String readLine(long index)
            throws FileReaderException {

        FileLineIndexEntry entry = fileLineIndex.get(index);
        if (entry == null)
            throw new FileReaderException.LineNotFound();

        return fileReader.read(entry.getStartPosition(), entry.getEndPosition());
    }

    public static class FileLineIndexEntryImpl implements FileLineIndexEntry, Serializable {
        private final long startPosition;
        private final long endPosition;

        FileLineIndexEntryImpl(long startPosition, long endPosition) {
            this.startPosition = startPosition;
            this.endPosition = endPosition;
        }

        @Override
        public long getStartPosition() {
            return startPosition;
        }

        @Override
        public long getEndPosition() {
            return endPosition;
        }
    }
}
