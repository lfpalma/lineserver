package com.salsify.web.services.lines;

import com.salsify.lineserver.file.FileReaderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LinesServiceErrorPolicy {
    private static final Logger logger = LoggerFactory.getLogger("LinesService");

    public static LinesServiceErrorPolicy getDefault() {
        return new LinesServiceErrorPolicy() {
            @Override
            public LinesServiceException handleException(Exception e) {
                LinesServiceErrorPolicy
                        .logger
                        .error(e.getMessage(), e);

                if (e instanceof FileReaderException.LineNotFound)
                    return new LinesServiceException.LineNotFoundException();

                return new LinesServiceException();
            }
        };
    }

    public abstract LinesServiceException handleException(Exception e);
}
