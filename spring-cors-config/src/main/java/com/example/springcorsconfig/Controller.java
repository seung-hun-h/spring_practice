package com.example.springcorsconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/cors")
    public String helloGet() {
        logger.info("helloGet()");
        return "cors request";
    }

    @PostMapping("/cors")
    public String helloPost(@RequestBody String title) {
        logger.info("helloPost(). title = {}", title);
        return "cors request";
    }
}
