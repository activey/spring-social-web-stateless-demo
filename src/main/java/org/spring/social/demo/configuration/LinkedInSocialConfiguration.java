package org.spring.social.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.web.OAuth2SessionStrategy;
import org.springframework.social.web.session.InMemorySessionStrategy;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

@EnableSocial
@Configuration
@EnableConfigurationProperties(LinkedinProperties.class)
public class LinkedInSocialConfiguration extends SocialConfigurerAdapter {

  @Autowired
  private ConnectionFactoryLocator connectionFactoryLocator;

  @Autowired
  private final LinkedinProperties linkedinProperties;

  public LinkedInSocialConfiguration(LinkedinProperties linkedinProperties) {
    this.linkedinProperties = linkedinProperties;
  }

  @Bean
  public OAuth2SessionStrategy sessionStrategy() {
    return new InMemorySessionStrategy();
  }

  @Override
  public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
    LinkedInConnectionFactory connectionFactory = new LinkedInConnectionFactory(
        linkedinProperties.getAppId(), linkedinProperties.getAppSecret()
    );
    ofNullable(linkedinProperties.getScope()).ifPresent(scope -> connectionFactory.setScope(scope.stream().collect(joining(","))));
    connectionFactoryConfigurer.addConnectionFactory(connectionFactory);
  }

  @Override
  public UserIdSource getUserIdSource() {
    return () -> ""; // Dummy implementation, used only in ApiHelper
  }
}
