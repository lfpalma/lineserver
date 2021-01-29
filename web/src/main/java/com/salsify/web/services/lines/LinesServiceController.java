package com.salsify.web.services.lines;

import com.salsify.lineserver.LineReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/lines")
public class LinesServiceController {

    private final LineReader lineReader;

    @Autowired
    protected LinesServiceController(LineReader lineReader) {
        this.lineReader = lineReader;
    }

    @GetMapping(path = "/{index}")
    public @ResponseBody String getLine(@PathVariable long index) {

        try {
            return lineReader.readLine(index);
        }
        catch(Exception e) {
            throw LinesServiceErrorPolicy
                    .getDefault()
                    .handleException(e);
        }
    }
}
