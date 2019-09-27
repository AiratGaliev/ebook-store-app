package com.github.airatgaliev.bookstore.repositories;

import com.github.airatgaliev.bookstore.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
}
