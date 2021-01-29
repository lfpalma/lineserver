package com.salsify.lineserver.file;

public interface FileLineIndex<E> {

    void put(long index, E entry);

    E get(long index);
}
