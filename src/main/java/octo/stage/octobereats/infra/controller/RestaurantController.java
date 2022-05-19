package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Plat;
import octo.stage.octobereats.domain.Restaurant;
import octo.stage.octobereats.infra.repository.RestaurantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> restaurants(){
        return restaurantRepository.getRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant one(@PathVariable long id) {
        return restaurantRepository.findById(id);
    }

    @GetMapping("/restaurants/{id}/plats")
    public List<Plat> plats(@PathVariable long id) {
        return restaurantRepository.getPlats(id);
    }

}
