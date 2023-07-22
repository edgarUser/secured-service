package com.mycom.poc.securedservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class GreetingController {

  @GetMapping("/greeting")
  public String greeting() {
    return "Hello world!";
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @GetMapping("/greeting/{complement}")
  public String greetingWithComplement(@PathVariable String complement) {
    return String.format("Hello world %s!", complement);
  }

}
