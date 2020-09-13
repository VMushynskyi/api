package com.api.testapi.config;

import com.api.testapi.model.User;
import com.api.testapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new User("Tom", "Burglar")));
            log.info("Preloading " + repository.save(new User("John", "SalesMan")));
            log.info("Preloading " + repository.save(new User("Douglas", "Driver")));
            log.info("Preloading " + repository.save(new User("Tim", "Engineer")));
            log.info("Preloading " + repository.save(new User("Red", "Agent")));
        };
    }
}
