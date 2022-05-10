package octo.stage.octobereats;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {

    private final RestaurantRepository repository;

    RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/restaurants")
    List<Restaurant> all() {
        return repository.findAll();
    }

    @GetMapping("/restaurants/{id}")
    Restaurant res(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }
}
