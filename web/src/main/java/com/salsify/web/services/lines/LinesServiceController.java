package com.salsify.web.services.lines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/lines")
public class LinesServiceController {

    private LinesServiceConfiguration.Impl impl;

    @Autowired
    protected LinesServiceController(LinesServiceConfiguration.Impl impl) {
        this.impl = impl;
    }

    @GetMapping(path = "/{index}")
    public @ResponseBody String getLine(@PathVariable long index) {
        LinesServiceErrorPolicy.getDefault().handleException(new Exception(impl.getLine()));
        return "Hello world";
    }
}
