package com.salsify.lineserver.file;

import java.io.Serializable;

public interface FileLineIndex<E> {

    void put(long index, E entry);

    E get(long index);
}
