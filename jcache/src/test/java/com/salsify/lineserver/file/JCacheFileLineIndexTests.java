package com.salsify.lineserver.file;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class JCacheFileLineIndexTests {
    @Test
    void testPut() {
        Assertions.assertDoesNotThrow(
            new Executable() {
                  @Override
                  public void execute() throws Throwable {
                      FileLineIndex < FileLineIndexEntry > fileLineIndex = new JCacheFileLineIndex<>();
                      fileLineIndex.put(1, new JCacheFileLineIndexEntry(0,80));
                  }
              });
    }

    @Test
    void testGet() {
        Assertions.assertDoesNotThrow(
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        FileLineIndex < FileLineIndexEntry > fileLineIndex = new JCacheFileLineIndex<>();
                        FileLineIndexEntry expected = new JCacheFileLineIndexEntry(0,80);
                        fileLineIndex.put(1, expected);
                        FileLineIndexEntry actual = fileLineIndex.get(1);
                        MatcherAssert.assertThat(actual, Matchers.samePropertyValuesAs(expected));
                    }
                });
    }
}
