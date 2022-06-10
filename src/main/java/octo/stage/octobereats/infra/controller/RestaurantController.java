package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.Plat;
import octo.stage.octobereats.infra.controller.output.RestaurantOutput;
import octo.stage.octobereats.infra.repository.RestaurantRepository;
import octo.stage.octobereats.usecases.restaurant.RecupererLesPlatsDUnRestaurant;
import octo.stage.octobereats.usecases.restaurant.RecupererLesRestaurants;
import octo.stage.octobereats.usecases.restaurant.RecupererRestaurant;
import octo.stage.octobereats.usecases.restaurant.SuivreLesCommandesDuRestaurant;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    RestaurantRepository restaurantRepository;

    final
    RecupererLesRestaurants recupererLesRestaurants;

    final
    RecupererRestaurant recupererRestaurant;

    final
    RecupererLesPlatsDUnRestaurant recupererLesPlatsDUnRestaurant;

    final
    SuivreLesCommandesDuRestaurant suivreLesCommandesDuRestaurant;


    public RestaurantController(RestaurantRepository restaurantRepository, RecupererLesRestaurants recupererLesRestaurants, RecupererRestaurant recupererRestaurant, RecupererLesPlatsDUnRestaurant recupererLesPlatsDUnRestaurant, SuivreLesCommandesDuRestaurant suivreLesCommandesDuRestaurant) {
        this.restaurantRepository = restaurantRepository;
        this.recupererLesRestaurants = recupererLesRestaurants;
        this.recupererRestaurant = recupererRestaurant;
        this.recupererLesPlatsDUnRestaurant = recupererLesPlatsDUnRestaurant;
        this.suivreLesCommandesDuRestaurant = suivreLesCommandesDuRestaurant;
    }

    // get une liste de restaurants
    @GetMapping("/restaurants")
    public List<RestaurantOutput> restaurants(){
        return recupererLesRestaurants.exécuter();
    }

    // get la restaurant qui a identifiant = id
    @GetMapping("/restaurants/{id}")
    public RestaurantOutput one(@PathVariable long id) {
        return recupererRestaurant.exécuter(id);
    }

    // get la liste de plat du restaurant qui a identifiant = id
    @GetMapping("/restaurants/{id}/plats")
    public List<Plat> plats(@PathVariable long id) {
        return recupererLesPlatsDUnRestaurant.exécuter(id);
    }

    // get la liste des commandes lié au restaurant qui a identifiant = id en réactive
    @GetMapping(path="/restaurants/{id}/commandes", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<Commande> getCommandeRestaurant(@PathVariable long id) {
        return suivreLesCommandesDuRestaurant.exécuter(id);
    }

}
