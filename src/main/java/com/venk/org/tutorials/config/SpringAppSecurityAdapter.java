package com.venk.org.tutorials.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author venkateshkaradbhajne
 */

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(
    value = "authentication.basic.auth",
    havingValue = "true",
    matchIfMissing = false)
public class SpringAppSecurityAdapter
    extends WebSecurityConfigurerAdapter {

  @Autowired
  Environment environment;

  @Autowired
  private SpringAppAuthEntryPoint authenticationEntryPoint;

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user = User
        .withUsername(environment.getProperty("spring.security.user.name"))
        .password(environment.getProperty("spring.security.user.password"))
        .roles("USER_ROLE")
        .build();
    return new InMemoryUserDetailsManager(user);
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable()
        .authorizeRequests().anyRequest().authenticated()
        .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);

    httpSecurity.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new Argon2PasswordEncoder();
  }

}