package com.education.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * @author Sergey Zhernovoy
 */
public class Startup implements CommandLineRunner {

    @Autowired
    private CustomService customService;

    public CustomService getCustomService() {
        return customService;
    }

    public void setCustomService(CustomService customService) {
        this.customService = customService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(customService.getContext().keySet());
    }
}
