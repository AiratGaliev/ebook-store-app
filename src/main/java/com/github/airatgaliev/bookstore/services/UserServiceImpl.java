package com.github.airatgaliev.bookstore.services;

import com.github.airatgaliev.bookstore.entities.PasswordResetToken;
import com.github.airatgaliev.bookstore.entities.User;
import com.github.airatgaliev.bookstore.entities.UserRole;
import com.github.airatgaliev.bookstore.repositories.IPasswordResetTokenRepository;
import com.github.airatgaliev.bookstore.repositories.IRoleRepository;
import com.github.airatgaliev.bookstore.repositories.IUserRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IRoleRepository roleRepository;

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

  @Override
  public User createUser(User user, Set<UserRole> userRoles) throws Exception {
    User localUser = userRepository.findByUsername(user.getUsername());

    if (localUser != null) {
      throw new Exception("User already exists. Nothing will be done");
    } else {
      for (UserRole userRole : userRoles) {
        roleRepository.save(userRole.getRole());
      }
      user.getUserRoleSet().addAll(userRoles);
      localUser = userRepository.save(user);
    }
    return localUser;
  }

  @Override
  public boolean isFoundedUserByUsername(String username) {
    User user = userRepository.findByUsername(username);
    return null != user;
  }

  @Override
  public boolean isFoundedUserByEmail(String email) {
    User user = userRepository.findByEmail(email);
    return null != user;
  }
}
