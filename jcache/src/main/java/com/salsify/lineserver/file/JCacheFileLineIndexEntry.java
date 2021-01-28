package com.salsify.lineserver.file;

import java.io.Serializable;

public class JCacheFileLineIndexEntry implements FileLineIndexEntry, Serializable {
    private final long startPosition;
    private final long endPosition;

    JCacheFileLineIndexEntry(long startPosition, long endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    @Override
    public long getStartPosition() {
        return this.startPosition;
    }

    @Override
    public long getEndPosition() {
        return this.endPosition;
    }
}
