package octo.stage.octobereats.usecases.restaurant;

import octo.stage.octobereats.domain.Restaurant;
import octo.stage.octobereats.infra.controller.output.RestaurantOutput;
import octo.stage.octobereats.infra.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

@Component
public class RecupererRestaurant {

    RestaurantRepository restaurantRepository;

    public RecupererRestaurant(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public RestaurantOutput exécuter(long id){
        Restaurant restaurant = restaurantRepository.findById(id);
        return new RestaurantOutput(restaurant);
    }

}
