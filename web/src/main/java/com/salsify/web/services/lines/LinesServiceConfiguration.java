package com.salsify.web.services.lines;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LinesServiceConfiguration {
    @Bean
    public Impl getImpl() {
        return new Impl("Hey Pachuco!");
    }

    public static class Impl {
        public String getLine() {
            return line;
        }

        private String line;

        public Impl(String line) {
            this.line = line;
        }
    }
}
