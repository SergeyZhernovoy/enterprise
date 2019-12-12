package com.education.boot.controllers;

import com.education.boot.core.Sorting;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/hello")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "The request has succeeded or (your message)"),
        @ApiResponse(code = 401, message = "The request requires user authentication or (your message)"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden or (your message)"),
        @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI or (your message)")})
public class EchoController {
    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/post")
    public String helloPost(@RequestBody String hello) {
        return Sorting.of(hello);
    }

    @PutMapping("/put")
    public String helloPut(@RequestBody String hello) {
        return hello;
    }
}
