package com.app.apiserver.service;

import com.app.apiserver.model.dto.AddUser;
import com.app.apiserver.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(int userId) throws Exception {
        if (userId == 404) {
            return null;
        }

        if (userId == 500) {
            throw new Exception("sample exception");
        }

        return new User(
                userId,
                "john smith",
                "email1@example.com",
                System.currentTimeMillis(),
                0L
        );
    }

    @Override
    public boolean deleteUser(int userId) throws Exception {
        return false;
    }

    @Override
    public User addUser(AddUser dto) throws Exception {
        return null;
    }
}
