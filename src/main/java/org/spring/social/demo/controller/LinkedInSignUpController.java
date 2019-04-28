package org.spring.social.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.web.OAuth2SessionStrategy;
import org.springframework.social.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

@Controller
@RequestMapping("/linkedin/signup")
public class LinkedInSignUpController {

  private final ProviderSignInUtils signInUtils;

  @Autowired
  public LinkedInSignUpController(ConnectionFactoryLocator connectionFactoryLocator,
                                  UsersConnectionRepository connectionRepository,
                                  OAuth2SessionStrategy sessionStrategy) {
    this.signInUtils = new ProviderSignInUtils(sessionStrategy, connectionFactoryLocator, connectionRepository);
  }

  @RequestMapping
  public String signUp(NativeWebRequest request) {
    Connection<?> connection = signInUtils.getConnectionFromSession(request);
    if (connection == null) {
      return "redirect:/error.html";
    }
    UserProfile userProfile = connection.fetchUserProfile();
    ConnectionData connectionData = connection.createData();
    if (userExists(userProfile.getEmail())) {
      signInUser(request, userProfile, connectionData);
      return "redirect:/logged-in.html";
    }
    registerNewAccount(connection, userProfile);
    signInUser(request, userProfile, connectionData);
    return "redirect:/logged-in.html";
  }

  private boolean userExists(String email) {
    // checking if user is already registered
    return true;
  }

  private void signInUser(NativeWebRequest request, UserProfile userProfile, ConnectionData connectionData) {
    signInUtils.doPostSignUp(userProfile.getEmail(), request);
  }

  private void registerNewAccount(Connection<?> connection, UserProfile userProfile) {
    // register new account if necessary
  }
}
