package com.salsify.test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestHelper {
    public static <T> Path getResourcePath(Class<T> cls, String resourceName)
            throws URISyntaxException {

        URL resourceURL = cls.getClassLoader().getResource(resourceName);
        return Paths.get(resourceURL.toURI());
    }
}
