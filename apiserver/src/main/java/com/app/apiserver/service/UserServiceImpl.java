package com.app.apiserver.service;

import com.app.apiserver.model.dto.AddUser;
import com.app.apiserver.model.entity.User;
import com.app.apiserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUser(int userId) throws Exception {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteUser(int userId) throws Exception {
        userRepository.deleteById(userId);
    }

    @Override
    public User addUser(AddUser dto) throws Exception {
        User newData = new User(
                0,
                dto.name(),
                dto.email(),
                System.currentTimeMillis(),
                0L
        );
        return userRepository.save(newData);
    }
}
