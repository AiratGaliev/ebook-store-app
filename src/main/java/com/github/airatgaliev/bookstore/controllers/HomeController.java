package com.github.airatgaliev.bookstore.controllers;

import com.github.airatgaliev.bookstore.entities.PasswordResetToken;
import com.github.airatgaliev.bookstore.entities.Role;
import com.github.airatgaliev.bookstore.entities.User;
import com.github.airatgaliev.bookstore.entities.UserRole;
import com.github.airatgaliev.bookstore.services.IUserService;
import com.github.airatgaliev.bookstore.services.UserSecurityService;
import com.github.airatgaliev.bookstore.utility.MailConstructor;
import com.github.airatgaliev.bookstore.utility.SecurityUtility;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

  @Autowired
  private IUserService userService;

  @Autowired
  private UserSecurityService userSecurityService;

  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  private MailConstructor mailConstructor;

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

  @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
  public String signUpPost(HttpServletRequest servletRequest,
      @ModelAttribute("firstName") String firstName,
      @ModelAttribute("lastName") String lastName,
      @ModelAttribute("username") String username,
      @ModelAttribute("email") String email,
      @ModelAttribute("password") String password,
      Model model) throws Exception {
    model.addAttribute("firstName", firstName);
    model.addAttribute("lastName", lastName);
    model.addAttribute("username", username);
    model.addAttribute("email", email);
    model.addAttribute("password", password);
    if (userService.isFoundedUserByUsername(username)) {
      model.addAttribute("usernameExists", true);
      return "sign-up";
    }
    if (userService.isFoundedUserByEmail(email)) {
      model.addAttribute("emailExists", true);
      return "sign-up";
    }
    String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
    User user = new User(username, email, encryptedPassword);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    Role role = new Role();
    role.setRoleId(1);
    role.setName("USER_ROLE");
    Set<UserRole> userRoles = new HashSet<>();
    userRoles.add(new UserRole(user, role));
    userService.createUser(user, userRoles);
    String token = UUID.randomUUID().toString();
    userService.createPasswordResetTokenForUser(user, token);
    String appUrl =
        "http://" + servletRequest.getServerName() + ":" + servletRequest.getServerPort()
            + servletRequest.getContextPath();
    SimpleMailMessage mailMessage = mailConstructor
        .constructResetTokenEmail(appUrl, servletRequest.getLocale(), token, user, password);
    mailSender.send(mailMessage);
    model.addAttribute("emailSent", "true");
    return "redirect:/";
  }

  @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
  public String signUp(@RequestParam(required = false, name = "token") String token, Model model) {
    PasswordResetToken passwordResetToken = userService.getPasswordResetToken(token);
    if (passwordResetToken == null) {
      String message = "Invalid token";
      model.addAttribute("message", message);
      return "sign-up";
    } else {
      User user = passwordResetToken.getUser();
      String username = user.getUsername();
      UserDetails userDetails = userSecurityService.loadUserByUsername(username);
      Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
          userDetails.getUsername(), userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
      model.addAttribute("user", user);
      return "profile";
    }
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

  @RequestMapping("/forget-password")
  public String forgetPassword() {
    return "forget-password";
  }

  @RequestMapping("/reset-password")
  public String resetPassword() {
    return "reset-password";
  }
}
