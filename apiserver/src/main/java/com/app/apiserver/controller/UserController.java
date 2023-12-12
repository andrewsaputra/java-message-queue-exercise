package com.app.apiserver.controller;

import com.app.apiserver.model.entity.User;
import com.app.apiserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // invoke userService.coba(userId) to get result.
    // if result is null then return 404
    // if result is not null then return result
    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable("id") int userId) throws Exception {
        User user = userService.getUser(userId);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return user;
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable("id") int userId) {
        return "User";
    }

    @PostMapping
    public String createUser(@RequestBody String requestBody) {
        return "User";
    }
}
