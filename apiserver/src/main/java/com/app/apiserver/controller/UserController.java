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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse> getUser(@PathVariable("id") int userId) throws Exception {
        Optional<User> user = userService.getUser(userId);

        HttpStatus statusCode;
        ApiResponse apiResponse;
        if (user.isPresent()) {
            statusCode = HttpStatus.OK;
            apiResponse = new ApiResponse("", user.get());
        } else {
            statusCode = HttpStatus.NOT_FOUND;
            apiResponse = new ApiResponse("data not found", null);
        }

        return ResponseEntity
                .status(statusCode)
                .body(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") int userId) throws Exception {
        boolean success = userService.deleteUser(userId);

        HttpStatus statusCode;
        ApiResponse apiResponse;
        if (success) {
            statusCode = HttpStatus.OK;
            apiResponse = new ApiResponse("data removed", null);
        } else {
            statusCode = HttpStatus.NOT_FOUND;
            apiResponse = new ApiResponse("data not found", null);
        }

        return ResponseEntity
                .status(statusCode)
                .body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody AddUser dto) throws Exception {
        User newUser = userService.addUser(dto);
        ApiResponse apiResponse = new ApiResponse("data added", newUser);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(apiResponse);
    }
}
