package com.education.spring;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Sergey Zhernovoy
 */
@Data
@Service
public class CustomService {
    private final Map<String, Object> context;

    @Autowired
    public CustomService(@CustomComponent Map<String, Object> context) {
        this.context = context;
        this.context.put("test", "test");
    }
}
