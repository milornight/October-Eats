package octo.stage.octobereats;

import static octo.stage.octobereats.domain.CommandeStatus.ENVOYE;
import static org.mockito.Mockito.times;

import java.util.List;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import octo.stage.octobereats.infra.flux.StatusFlux;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CommandeController.class)
@Import({CommandeRepo.class,CommandeFlux.class,StatusFlux.class})
public class CommandeControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    CommandeRepository commandeRepo;

    @MockBean
    CommandeFlux commandeFlux;

    @Test
    public void testGetCommandes() {
        List<Commande> listCommande = List.of(new Commande(1,1,2,3),
                new Commande(2,1,3,3));

        Mockito.when(commandeRepo.getCommandes()).thenReturn(listCommande);

        webClient.get()
                .uri("/commandes")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].idClient").isEqualTo(1)
                .jsonPath("$.[0].idRestaurant").isEqualTo(1)
                .jsonPath("$.[0].idPlat").isEqualTo(2)
                .jsonPath("$.[0].quantite").isEqualTo(3)
                .jsonPath("$.[0].commandeStatus").isEqualTo("ENVOYE")
                .jsonPath("$.[1].idClient").isEqualTo(2)
                .jsonPath("$.[1].idRestaurant").isEqualTo(1)
                .jsonPath("$.[1].idPlat").isEqualTo(3)
                .jsonPath("$.[1].quantite").isEqualTo(3)
                .jsonPath("$.[1].commandeStatus").isEqualTo("ENVOYE");

        Mockito.verify(commandeRepo, times(1)).getCommandes();
    }

   /* @Test
    public void testAddCommande() {
        Commande commande = new Commande(1,1,2,3);

        Mockito.when(commandeRepo.addCommande(commande)).thenReturn(commande);

        webClient.post()
                .uri("/commandes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(commande)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.idClient").isEqualTo(1)
                .jsonPath("$.idRestaurant").isEqualTo(1)
                .jsonPath("$.idPlat").isEqualTo(2)
                .jsonPath("$.quantite").isEqualTo(3)
                .jsonPath("$.commandeStatus").isEqualTo("ENVOYE");


        Mockito.verify(commandeRepo, times(1)).addCommande(commande);
    }*/

    /*@Test
    public void testGetCommandesRestaurant() {
        Commande commande1 = new Commande(1,1,3,2);
        ConnectableFlux<Commande> commandesPublisher =

        Mockito.when(commandeFlux.getCommandesPublisher()).thenReturn(commandesPublisher));

        webClient.get()
                .uri("/restaurants/{id}/commandes",1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].idRestaurant").isEqualTo(1)
                .jsonPath("$.[0].idPlat").isEqualTo(1)
                .jsonPath("$.[0].quantite").isEqualTo(2);


        Mockito.verify(commandeFlux, times(1)).getCommandesPublisher();
       }
     */
}
