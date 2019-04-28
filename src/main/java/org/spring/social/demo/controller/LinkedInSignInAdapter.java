package org.spring.social.demo.controller;

import org.springframework.social.connect.Connection;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

@Component
public class LinkedInSignInAdapter implements SignInAdapter<LinkedIn> {

  @Override
  public String signIn(String userId, Connection<LinkedIn> connection, NativeWebRequest nativeWebRequest) {
    // TODO decide where to redirect after signing in
    return "/";
  }
}
