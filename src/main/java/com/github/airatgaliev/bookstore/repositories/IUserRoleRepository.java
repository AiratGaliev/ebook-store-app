package com.github.airatgaliev.bookstore.repositories;

import com.github.airatgaliev.bookstore.entities.User;
import com.github.airatgaliev.bookstore.entities.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface IUserRoleRepository extends CrudRepository<UserRole, Long> {

  UserRole findUserRoleByUser(User user);
}
