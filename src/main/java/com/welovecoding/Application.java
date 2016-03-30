package com.welovecoding;

import com.welovecoding.config.Constants;
import com.welovecoding.config.WLCProperties;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.MetricFilterAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@ComponentScan

@EnableAutoConfiguration(exclude = {MetricFilterAutoConfiguration.class, MetricRepositoryAutoConfiguration.class})
@EnableConfigurationProperties({WLCProperties.class})
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  @Inject
  private Environment env;

  @Value("${spring.datasource.driver-class-name}")
  private String driver;
  @Value("${spring.datasource.url}")
  private String url;
  @Value("${spring.datasource.username}")
  private String username;
  @Value("${spring.datasource.password}")
  private String token;

  /**
   * <p/>
   * Spring profiles can be configured with a program arguments
   * --spring.profiles.active=your-active-profile
   * <p/>
   */
  @PostConstruct
  public void initApplication() {
    log.info("SQL INFO");
    log.info("driver: {}",driver);
    log.info("url: {}",url);
    log.info("username: {}",username);
    log.info("token: {}*********",token.substring(0, 3));
    
    
    if (env.getActiveProfiles().length == 0) {
      log.warn("No Spring profile configured, running with default configuration");
    } else {
      log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
      Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
      if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION)) {
        log.error("You have misconfigured your application! "
          + "It should not run with both the 'dev' and 'prod' profiles at the same time.");
      }
    }
  }

  public static void main(String[] args) throws UnknownHostException {
    SpringApplication app = new SpringApplication(Application.class);
    SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
    Environment env = app.run(args).getEnvironment();
    log.info("Access URLs:\n----------------------------------------------------------\n\t"
      + "Local: \t\thttp://127.0.0.1:{}\n\t"
      + "External: \thttp://{}:{}\n----------------------------------------------------------",
      env.getProperty("server.port"),
      InetAddress.getLocalHost().getHostAddress(),
      env.getProperty("server.port"));

  }
}
