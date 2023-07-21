package com.mycom.poc.securedservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  //Authentication
  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

    UserDetails admin = User.withUsername("admin")
        .password(passwordEncoder.encode("12345"))
        .roles("ADMIN").build();

    UserDetails user = User.withUsername("user")
        .password(passwordEncoder.encode("abc123"))
        .roles("USER").build();

    return new InMemoryUserDetailsManager(admin, user);
  }

  //Authorization
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth.requestMatchers("/v1/**")
            .permitAll().anyRequest().authenticated()
        ).build();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
