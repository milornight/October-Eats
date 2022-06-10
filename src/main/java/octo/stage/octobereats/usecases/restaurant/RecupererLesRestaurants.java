package octo.stage.octobereats.usecases.restaurant;

import octo.stage.octobereats.domain.Restaurant;
import octo.stage.octobereats.infra.controller.output.RestaurantOutput;
import octo.stage.octobereats.infra.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecupererLesRestaurants {

    RestaurantRepository restaurantRepository;

    public RecupererLesRestaurants(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<RestaurantOutput> exécuter(){
        List<Restaurant> restaurants = restaurantRepository.getRestaurants();
        return restaurants.stream().map(RestaurantOutput::new).toList();
    }
}
