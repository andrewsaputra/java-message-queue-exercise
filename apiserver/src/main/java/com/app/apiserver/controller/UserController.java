package com.app.apiserver.controller;

import com.app.apiserver.model.basic.ApiResponse;
import com.app.apiserver.model.dto.AddUser;
import com.app.apiserver.model.entity.User;
import com.app.apiserver.service.IUserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RestController
@RequestMapping(path = "/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public ApiResponse getUser(@PathVariable("id") int userId) throws Exception {
        Optional<User> user = userService.getUser(userId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ApiResponse("data found", user.get());
    }

    @DeleteMapping(path = "/{id}")
    public ApiResponse deleteUser(@PathVariable("id") int userId) throws Exception {
        userService.deleteUser(userId);
        return new ApiResponse("data removal processed", null);
    }

    @PostMapping
    public ApiResponse addUser(@Valid @RequestBody AddUser dto) throws Exception {
        User newUser = userService.addUser(dto);
        return new ApiResponse("data added", newUser);
    }
}
