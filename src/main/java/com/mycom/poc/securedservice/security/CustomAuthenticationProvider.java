package com.mycom.poc.securedservice.security;

import java.util.List;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  public CustomAuthenticationProvider() {
    super();
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String userName = authentication.getName();
    String password = String.valueOf(authentication.getCredentials());
    if (userName.equals("user") && password.equals("abc123")) {
      return new UsernamePasswordAuthenticationToken(userName, password, List.of());
    } else {
      throw new AuthenticationCredentialsNotFoundException("Error in Authentication");
    }
  }

  @Override
  public boolean supports(Class<?> authenticationType) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
  }
}
