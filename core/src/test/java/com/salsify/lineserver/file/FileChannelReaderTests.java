package com.salsify.lineserver.file;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class FileChannelReaderTests extends FileReaderTests{
    @Override
    FileReader create(Path pathToFile)
            throws Exception {

        return FileChannelReader.create(pathToFile, StandardCharsets.US_ASCII);
    }
}
