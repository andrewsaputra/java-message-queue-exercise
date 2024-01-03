package com.app.apiserver.service;

import com.app.apiserver.model.dto.AddUser;
import com.app.apiserver.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Optional<User> getUser(int userId) throws Exception {
        Optional<User> result = Optional.empty();

        if (userId == 404) {
            return result;
        }

        if (userId == 500) {
            throw new Exception("sample exception");
        }

        result = Optional.of(
                new User(
                        userId,
                        "john smith",
                        "email1@example.com",
                        System.currentTimeMillis(),
                        0L
                )
        );

        return result;
    }

    @Override
    public boolean deleteUser(int userId) throws Exception {
        return userId != 404;
    }

    @Override
    public User addUser(AddUser dto) throws Exception {
        User result = new User(
                999,
                dto.name(),
                dto.email(),
                System.currentTimeMillis(),
                0L
        );

        return result;
    }
}
