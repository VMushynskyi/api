package com.api.testapi.config;

import com.api.testapi.model.Location;
import com.api.testapi.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(LocationRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Location("Bilbo Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Location(" Baggins", "lar")));
            log.info("Preloading " + repository.save(new Location(" Dgins", "lttyr")));
        };
    }
}
