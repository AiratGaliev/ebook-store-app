package com.github.airatgaliev.bookstore.controllers;

import com.github.airatgaliev.bookstore.entities.PasswordResetToken;
import com.github.airatgaliev.bookstore.services.IUserService;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

  private IUserService userService;

  @RequestMapping("/")
  public String index() {
    return "index";
  }

  @RequestMapping("/store")
  public String store() {
    return "store";
  }

  @RequestMapping("/sign-in")
  public String signIn() {
    return "sign-in";
  }

  @RequestMapping("/sign-up")
  public String signUp() {
    return "sign-up";
  }

  @RequestMapping("/account")
  public String account() {
    return "account";
  }

  @RequestMapping("/about")
  public String about() {
    return "about";
  }

  @RequestMapping("/contact")
  public String contact() {
    return "contact";
  }

  @RequestMapping("/cart")
  public String cart() {
    return "cart";
  }

  @RequestMapping("/reset-pass")
  public String resetPass(Locale locale, @RequestParam("token") String token) {
    PasswordResetToken passwordResetToken = userService.getPasswordResetToken(token);
    return "reset-pass";
  }
}
