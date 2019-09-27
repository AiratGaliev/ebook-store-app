package com.github.airatgaliev.bookstore.services;

import com.github.airatgaliev.bookstore.entities.PasswordResetToken;
import com.github.airatgaliev.bookstore.entities.User;

public interface IUserService {

  PasswordResetToken getPasswordResetToken(String token);

  void createPasswordResetTokenForUser(User user, String token);
}
