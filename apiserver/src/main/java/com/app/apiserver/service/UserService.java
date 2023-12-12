package com.app.apiserver.service;

import com.app.apiserver.model.basic.ServiceResponse;
import com.app.apiserver.model.dto.AddUser;
import com.app.apiserver.model.entity.User;

public interface UserService {
    User getUser(int userId) throws Exception;
    boolean deleteUser(int userId) throws Exception;
    User addUser(AddUser dto) throws Exception;
}
