package com.salsify.lineserver.file;

import com.salsify.lineserver.LineReader;
import com.salsify.test.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class IndexedFileLineReaderTests {
    @Test
    void testReadLine()
            throws Exception {
        Path pathToFile = TestHelper.getResourcePath(getClass(), "small-file");
        FileLineIndex<FileLineIndexEntry> fileLineIndex = new JCacheFileLineIndex<>();
        FileReader fileReader =
                FileChannelReader.create(pathToFile, StandardCharsets.US_ASCII);

        LineReader lineReader = new IndexedFileLineReader(fileLineIndex, fileReader);
        String expected = "10-0123456789012345678901234567890123456789012345678901234567890123456789aaaaaaa";
        String actual = lineReader.readLine(10);
        Assertions.assertEquals(expected, actual);
    }
}
