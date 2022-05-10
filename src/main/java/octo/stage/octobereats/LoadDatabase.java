/*package octo.stage.octobereats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(RestaurantRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Restaurant("Aki", "Japonais")));
            log.info("Preloading " + repository.save(new Restaurant("K-Chicken", "Fast food")));
            log.info("Preloading " + repository.save(new Restaurant("On The Bab", "Coreen")));
            log.info("Preloading " + repository.save(new Restaurant("Faubourg", "Francais")));
        };
    }
}
*/