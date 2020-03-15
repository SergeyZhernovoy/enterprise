package com.education.boot.controllers;

import com.education.boot.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@Api(value = "User Resource REST Endpoint", consumes = "Shows the user info")
public class UserResource {
    @ApiOperation(value = "Показывает список пользователей")
    @GetMapping
    public List<User> getUsers() {
        return Arrays.asList(
                new User("John", 3000),
                new User("Kevin", 2000)
        );
    }

    @GetMapping("/{userName}")
    public User getUser(@PathVariable("userName") String userName) {
        return new User(userName, 2000);
    }
}
