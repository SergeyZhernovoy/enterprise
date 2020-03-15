package com.education.spring;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Sergey Zhernovoy
 */
@CustomComponent
@Component
@Data
public class CustomExample {
    private String message;
}
