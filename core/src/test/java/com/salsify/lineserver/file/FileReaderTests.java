package com.salsify.lineserver.file;

import com.salsify.test.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

public abstract class FileReaderTests {
    @Test
    void testReadCharacter()
            throws Exception {

        FileReader reader = create(TestHelper.getResourcePath(getClass(), "small-file"));
        Assertions.assertEquals('1', reader.read());
    }

    @Test
    void testReadLine()
            throws Exception {

        String expected = "1-0123456789012345678901234567890123456789012345678901234567890123456789aaaaaaaa";

        FileReader reader = create(TestHelper.getResourcePath(getClass(), "small-file"));
        Assertions.assertEquals(expected, reader.readLine());
    }

    @Test
    void testReadAllLines() {
        Assertions.assertDoesNotThrow(
                () -> {
                    FileReader reader = create(TestHelper.getResourcePath(getClass(), "small-file"));
                    while (reader.readLine() != null) ;
                });
    }

    @Test
    void testFilePointerAfterReadFirstLine()
            throws Exception {

        long expected = 81L;
        FileReader reader = create(TestHelper.getResourcePath(getClass(), "small-file"));
        reader.readLine();
        Assertions.assertEquals(expected, reader.getFilePointer());
    }

    @Test
    void testReadFromStartPosToEndPos()
            throws Exception {
        String expected = "2-0123456789012345678901234567890123456789012345678901234567890123456789aaaaaaaa";

        FileReader reader = create(TestHelper.getResourcePath(getClass(), "small-file"));
        Assertions.assertEquals(expected, reader.read(81, 81+80));
    }

    abstract FileReader create(Path pathToFile) throws Exception;
}
