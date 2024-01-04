package com.app.apiserver.service;

import com.app.apiserver.model.dto.AddUser;
import com.app.apiserver.model.entity.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> getUser(int userId) throws Exception;

    void deleteUser(int userId) throws Exception;

    User addUser(AddUser dto) throws Exception;
}
