package com.github.airatgaliev.bookstore.utility;

import com.github.airatgaliev.bookstore.entities.User;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MailConstructor {

  private final Environment environment;

  @Autowired
  public MailConstructor(Environment environment) {
    this.environment = environment;
  }

  public SimpleMailMessage constructResetTokenEmail(String appUrl, Locale locale, String token,
      User user, String password) {
    String url = appUrl + "/sign-up?token=" + token;
    String message =
        "\nPlease click on this link to verify your email. \n Your password is: " + password;
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(user.getEmail());
    simpleMailMessage.setSubject("Book Store - Sign Up");
    simpleMailMessage.setText(url + message);
    simpleMailMessage.setFrom(environment.getProperty("support.email"));
    return simpleMailMessage;
  }

  public SimpleMailMessage constructSendResetPassword(String email, String password) {
    String message =
        "Your new password is: " + password;
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(email);
    simpleMailMessage.setSubject("Book Store - Reset Password");
    simpleMailMessage.setText(message);
    simpleMailMessage.setFrom(environment.getProperty("support.email"));
    return simpleMailMessage;
  }
}
