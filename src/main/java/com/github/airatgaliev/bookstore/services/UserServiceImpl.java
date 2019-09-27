package com.github.airatgaliev.bookstore.services;

import com.github.airatgaliev.bookstore.entities.PasswordResetToken;
import com.github.airatgaliev.bookstore.entities.User;
import com.github.airatgaliev.bookstore.repositories.IPasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  private IPasswordResetTokenRepository passwordResetTokenRepository;

  @Override
  public PasswordResetToken getPasswordResetToken(String token) {
    return passwordResetTokenRepository.findByToken(token);
  }

  @Override
  public void createPasswordResetTokenForUser(User user, String token) {
    PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
    passwordResetTokenRepository.save(passwordResetToken);
  }
}
