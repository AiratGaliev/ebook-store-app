package com.github.airatgaliev.bookstore.repositories;

import com.github.airatgaliev.bookstore.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
}
