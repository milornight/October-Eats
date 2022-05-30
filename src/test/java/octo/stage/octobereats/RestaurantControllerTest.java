package octo.stage.octobereats;

import static org.mockito.Mockito.times;

import java.util.List;

import octo.stage.octobereats.domain.Plat;
import octo.stage.octobereats.domain.Restaurant;
import octo.stage.octobereats.infra.controller.RestaurantController;
import octo.stage.octobereats.infra.repository.RestaurantRepo;
import octo.stage.octobereats.infra.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = RestaurantController.class)
@Import(RestaurantRepo.class)
public class RestaurantControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    RestaurantRepository restaurantRepo;

    @Test
    public void testRestaurants() {
        List<Plat> listPlat = List.of(new Plat("chesse",10));
        List<Restaurant> listRestaurant = List.of(new Restaurant("toto","francais",listPlat),
                new Restaurant("kiki","asiatique",null));

        Mockito.when(restaurantRepo.getRestaurants()).thenReturn(listRestaurant);

        webClient.get()
                .uri("/restaurants")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo(1)
                .jsonPath("$.[0].nom").isEqualTo("toto")
                .jsonPath("$.[0].type").isEqualTo("francais")
                .jsonPath("$.[0].plats.[0].id").isEqualTo(1)
                .jsonPath("$.[0].plats.[0].nom").isEqualTo("chesse")
                .jsonPath("$.[0].plats.[0].prix").isEqualTo(10)
                .jsonPath("$.[1].id").isEqualTo(2)
                .jsonPath("$.[1].nom").isEqualTo("kiki")
                .jsonPath("$.[1].type").isEqualTo("asiatique")
                .jsonPath("$.[1].plats").isEqualTo(null);

        Mockito.verify(restaurantRepo, times(1)).getRestaurants();
    }

    @Test
    public void testOne(){
        Restaurant restaurant = new Restaurant("kiki","asiatique",null);

        Mockito.when(restaurantRepo.findById(1)).thenReturn(restaurant);

        webClient.get()
                .uri("/restaurants/{id}",1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.nom").isEqualTo("kiki")
                .jsonPath("$.type").isEqualTo("asiatique");

        Mockito.verify(restaurantRepo, times(1)).findById(1);

    }

    @Test
    public void testPlats(){
        List<Plat> listPlat = List.of(new Plat("chesse",10));

        Mockito.when(restaurantRepo.getPlats(1)).thenReturn(listPlat);

        webClient.get()
                .uri("/restaurants/{id}/plats",1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo(1)
                .jsonPath("$.[0].nom").isEqualTo("chesse")
                .jsonPath("$.[0].prix").isEqualTo(10);

        Mockito.verify(restaurantRepo, times(1)).getPlats(1);

    }
}