package org.kamilG.demo.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan("org.kamilG.demo")
@EnableScheduling
public class AppConfig {
  @Bean
  public SessionFactory sessionFactory() {
    return new org.hibernate.cfg.Configuration()
        .configure()
        .buildSessionFactory();
  }
}
