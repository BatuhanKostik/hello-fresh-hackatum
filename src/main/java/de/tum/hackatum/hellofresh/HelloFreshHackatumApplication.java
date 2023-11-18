package de.tum.hackatum.hellofresh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
public class HelloFreshHackatumApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloFreshHackatumApplication.class, args);
    }

}
