package com.github.airatgaliev.bookstore.config;

import com.github.airatgaliev.bookstore.services.UserSecurityService;
import com.github.airatgaliev.bookstore.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserSecurityService userSecurityService;

  @Autowired
  public SecurityConfig(
      UserSecurityService userSecurityService) {
    this.userSecurityService = userSecurityService;
  }

  private BCryptPasswordEncoder passwordEncoder() {
    return SecurityUtility.passwordEncoder();
  }

  private static final String[] PUBLIC_MATCHERS = {
      "/css/**", "/js/**", "/images/**", "/", "/store", "/about", "/contact", "/login",
      "/sign-up", "/forget-password", "/reset-password", "/h2-console/**"
  };

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests()
        .antMatchers(PUBLIC_MATCHERS)
        .permitAll().anyRequest().authenticated();
    httpSecurity.csrf().disable().cors().disable()
        .formLogin().failureUrl("/sign-in?error")
        .defaultSuccessUrl("/")
        .loginPage("/sign-in").permitAll()
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/sign-out"))
        .logoutSuccessUrl("/?sign-out").deleteCookies("remember-me").permitAll()
        .and()
        .rememberMe();
    httpSecurity.headers().frameOptions().sameOrigin();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder.userDetailsService(userSecurityService)
        .passwordEncoder(passwordEncoder());
  }
}
