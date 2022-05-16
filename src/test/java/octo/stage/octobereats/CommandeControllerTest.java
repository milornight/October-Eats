package octo.stage.octobereats;

import static org.mockito.Mockito.times;

import java.util.List;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.Plat;
import octo.stage.octobereats.domain.Restaurant;
import octo.stage.octobereats.infra.controller.CommandeController;
import octo.stage.octobereats.infra.repository.CommandeRepo;
import octo.stage.octobereats.infra.repository.CommandeRepository;
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
@WebFluxTest(controllers = CommandeController.class)
@Import(CommandeRepo.class)
public class CommandeControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    CommandeRepository commandeRepo;

    @Test
    public void testGetCommande() {
        List<PlatCommande> listPlatCommande = List.of(new PlatCommande(new Plat("chesse",10),2));
        Commande commande = new Commande(1,listPlatCommande);

        Mockito.when(commandeController.newCommande(commande)).thenReturn(commande.toString());

        webClient.post()
                .uri("/commandes")
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(commandeController, times(1)).newCommande(commande);
    }

    public void testGetRestaurants() {
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
}
