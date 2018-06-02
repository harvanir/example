/*
 * Copyright 2018-2018 the original author or authors.
 */

package org.harvan.example.springboot.sample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (14 Apr 2018)
 *
 */
@Controller
public class SampleController {
    private final Log logger = LogFactory.getLog(getClass());
    @Value("${helo.message}")
    private String message;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
	if (logger().isDebugEnabled()) {
	    logger().debug("Logging...");
	}

	return message;
    }

    Log logger() {
	return logger;
    }
}