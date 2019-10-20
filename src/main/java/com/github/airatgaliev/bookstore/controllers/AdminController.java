package com.github.airatgaliev.bookstore.controllers;

import com.github.airatgaliev.bookstore.services.IUserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

  private final IUserService userService;

  @Autowired
  public AdminController(IUserService userService) {
    this.userService = userService;
  }

  @RequestMapping("")
  public String index(Principal principal) {
    if (userService.isAdminRole(principal.getName())) {
      return "admin";
    }
    return "redirect:/";
  }
}
