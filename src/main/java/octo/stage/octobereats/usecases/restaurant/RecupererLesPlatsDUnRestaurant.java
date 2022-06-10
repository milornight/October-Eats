package octo.stage.octobereats.usecases.restaurant;

import octo.stage.octobereats.domain.Plat;
import octo.stage.octobereats.infra.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecupererLesPlatsDUnRestaurant {

    RestaurantRepository restaurantRepository;

    public RecupererLesPlatsDUnRestaurant(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Plat> exécuter(long id){
        return restaurantRepository.getPlats(id);
    }
}
