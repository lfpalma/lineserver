package com.salsify.web.services.lines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LinesServiceErrorPolicy {
    private static final Logger logger = LoggerFactory.getLogger("LinesService");

    public static LinesServiceErrorPolicy getDefault() {
        return new LinesServiceErrorPolicy() {
            @Override
            public void handleException(Exception e) {
                LinesServiceErrorPolicy
                        .logger
                        .error(e.getMessage(), e);

                throw new LinesServiceException.LineNotFoundException();
            }
        };
    }

    public abstract void handleException(Exception e);
}
