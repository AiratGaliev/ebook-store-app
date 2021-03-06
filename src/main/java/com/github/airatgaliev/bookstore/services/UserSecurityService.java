package com.github.airatgaliev.bookstore.services;

import com.github.airatgaliev.bookstore.entities.User;
import com.github.airatgaliev.bookstore.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

  private final IUserRepository userRepository;

  @Autowired
  public UserSecurityService(
      IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (null == user) {
      throw new UsernameNotFoundException("Username not found");
    }
    return user;
  }
}
