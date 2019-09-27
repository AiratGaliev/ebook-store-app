package com.github.airatgaliev.bookstore.utility;

import java.security.SecureRandom;
import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public interface SecurityUtility {

  String SALT = "salt";

  @Bean
  static BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
  }

  @Bean
  static String randomPassword() {
    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();

    while (salt.length() < 18) {
      int index = (int) rnd.nextFloat() * SALTCHARS.length();
      salt.append(SALTCHARS.charAt(index));
    }
    return salt.toString();
  }
}
