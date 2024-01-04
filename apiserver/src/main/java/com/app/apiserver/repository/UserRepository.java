package com.app.apiserver.repository;

import com.app.apiserver.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
