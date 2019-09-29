package com.github.airatgaliev.bookstore.repositories;

import com.github.airatgaliev.bookstore.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface IRoleRepository extends CrudRepository<Role, Long> {

  Role findByName(String name);

  Role findById(long id);
}
