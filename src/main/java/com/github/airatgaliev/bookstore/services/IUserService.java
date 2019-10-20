package com.github.airatgaliev.bookstore.services;

import com.github.airatgaliev.bookstore.entities.PasswordResetToken;
import com.github.airatgaliev.bookstore.entities.User;
import com.github.airatgaliev.bookstore.entities.UserRole;
import java.util.Set;

public interface IUserService {

  PasswordResetToken getPasswordResetToken(String token);

  void createPasswordResetTokenForUser(User user, String token);

  User createUser(User user, Set<UserRole> userRoles) throws Exception;

  boolean isFoundedUserByUsername(String username);

  boolean isFoundedUserByEmail(String email);

  void resetPassword(String email, String password);

  boolean isAdminRole(String username);
}
