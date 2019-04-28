package org.spring.social.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.support.URIBuilder;
import org.springframework.social.web.AbstractOAuth2SignInController;
import org.springframework.social.web.OAuth2SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/linkedin/signin")
public class LinkedInSignInController extends AbstractOAuth2SignInController<LinkedIn> {

  @Autowired
  public LinkedInSignInController(ConnectionFactoryLocator connectionFactoryLocator,
                                  UsersConnectionRepository usersConnectionRepository,
                                  LinkedInSignInAdapter signInAdapter,
                                  OAuth2SessionStrategy sessionStrategy) {
    super(connectionFactoryLocator, usersConnectionRepository, signInAdapter, sessionStrategy);
  }

  @Override
  protected Class<LinkedIn> providerApiType() {
    return LinkedIn.class;
  }

  @Override
  protected URIBuilder signInRedirectURI() {
    return URIBuilder.fromUri("/linkedin/signin");
  }

  @Override
  protected URIBuilder signUpRedirectURI() {
    return URIBuilder.fromUri("/linkedin/signup");
  }
}

