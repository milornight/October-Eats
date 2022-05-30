package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.Plat;
import octo.stage.octobereats.domain.Restaurant;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import octo.stage.octobereats.infra.repository.RestaurantRepository;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    RestaurantRepository restaurantRepository;

    @Autowired
    CommandeFlux commandeFlux;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // get une liste de restaurants
    @GetMapping("/restaurants")
    public List<Restaurant> restaurants(){
        return restaurantRepository.getRestaurants();
    }

    // get la restaurant qui a identifiant = id
    @GetMapping("/restaurants/{id}")
    public Restaurant one(@PathVariable long id) {
        return restaurantRepository.findById(id);
    }

    // get la liste de plat du restaurant qui a identifiant = id
    @GetMapping("/restaurants/{id}/plats")
    public List<Plat> plats(@PathVariable long id) {
        return restaurantRepository.getPlats(id);
    }

    // get la list des commandes lié au restaurant qui a identifiant = id en réactive
    @GetMapping(path="/restaurants/{id}/commandes", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<Commande> getCommandeRestaurant(@PathVariable long id) {
        return commandeFlux.getCommandesPublisher().filter((commande)-> commande.getIdRestaurant() == id);
    }

}
