package octo.stage.octobereats;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    RestaurantRepo R = new RestaurantRepo();

    @GetMapping("/restaurants")
    public List<Restaurant> restaurants(){
        return R.getRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant one(@PathVariable long id) {
        return R.findById(id);
    }
}
