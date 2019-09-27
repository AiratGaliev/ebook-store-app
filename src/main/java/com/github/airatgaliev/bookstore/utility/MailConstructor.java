package com.github.airatgaliev.bookstore.utility;

import com.github.airatgaliev.bookstore.entities.User;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MailConstructor {

  @Autowired
  private Environment environment;

  public SimpleMailMessage constructResetTokenEmail(String appUrl, Locale locale, String token,
      User user, String password) {
    String url = appUrl + "/sign-up?token=" + token;
    String message = "\nPlease click on this link to verify your email";
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(user.getEmail());
    email.setSubject("Book Store - Sign Up");
    email.setText(url + message);
    email.setFrom(environment.getProperty("support.email"));
    return email;
  }
}
