package com.salsify.web.services.lines;

import com.salsify.lineserver.LineReader;
import com.salsify.lineserver.LineReaderException;
import com.salsify.lineserver.file.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Configuration
public class LinesServiceConfiguration {
    private final ApplicationArguments args;

    @Autowired
    public LinesServiceConfiguration(ApplicationArguments args) {
        this.args = args;
    }

    @Bean
    @Profile("production")
    public LineReader getLineReader() {

        try {
            return new IndexedFileLineReader(
                    getFileLineIndex(),
                    getFileReader()
            );
        }
        catch(Exception e) {
            throw
                LinesServiceErrorPolicy
                    .getDefault()
                    .handleException(e);
        }
    }

    private FileLineIndex<FileLineIndexEntry> getFileLineIndex()
            throws FileLineIndexException {

        return new JCacheFileLineIndex<>();
    }

    private FileReader getFileReader()
            throws FileReaderException {
        Path path = Paths.get(getPathToFileArgumentValue());

        return FileChannelReader.create(path, StandardCharsets.US_ASCII);
    }

    private String getPathToFileArgumentValue()
            throws IllegalArgumentException {
        List<String> nonOptionArguments = args.getNonOptionArgs();

        if (nonOptionArguments.size() != 1)
            throw new IllegalArgumentException("No file provided.");

        return nonOptionArguments.get(0);
    }

    @Bean
    @Profile("test")
    public LineReader getTestLineReader() {
        try {
            Path pathToFile = getResourcePath(getClass(), "small-file");
            FileLineIndex<FileLineIndexEntry> fileLineIndex = new JCacheFileLineIndex<>();
            return new IndexedFileLineReader(
                    fileLineIndex,
                    FileChannelReader.create(
                            pathToFile, StandardCharsets.US_ASCII));
        } catch (Exception e) {
            throw
                    LinesServiceErrorPolicy.getDefault().handleException(e);
        }
    }

    private <T> Path getResourcePath(Class<T> cls, String resourceName)
            throws URISyntaxException {

        URL resourceURL = cls.getClassLoader().getResource(resourceName);
        return Paths.get(resourceURL.toURI());
    }
}
