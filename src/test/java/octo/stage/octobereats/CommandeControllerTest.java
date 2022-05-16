package octo.stage.octobereats;

import static org.mockito.Mockito.times;

import java.util.List;

import octo.stage.octobereats.domain.Commande;
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
import org.springframework.web.reactive.function.BodyInserters;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CommandeController.class)
@Import(CommandeRepo.class)
public class CommandeControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    CommandeRepository commandeRepo;

    @Test
    public void testGetCommandes() {
        List<Commande> listCommande = List.of(new Commande(1,2,2),new Commande(2,5,1));

        Mockito.when(commandeRepo.getCommandes()).thenReturn(listCommande);

        webClient.get()
                .uri("/commandes")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].idRestaurant").isEqualTo(1)
                .jsonPath("$.[0].idPlat").isEqualTo(2)
                .jsonPath("$.[0].quantite").isEqualTo(2)
                .jsonPath("$.[1].idRestaurant").isEqualTo(2)
                .jsonPath("$.[1].idPlat").isEqualTo(5)
                .jsonPath("$.[1].quantite").isEqualTo(1);

        Mockito.verify(commandeRepo, times(1)).getCommandes();
    }

    @Test
    public void testAddCommande() {
        Commande commande = new Commande(1,2,3);

        Mockito.when(commandeRepo.addCommande(commande)).thenReturn(commande);

        webClient.post()
                .uri("/commandes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(commande)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.idRestaurant").isEqualTo(1)
                .jsonPath("$.idPlat").isEqualTo(2)
                .jsonPath("$.quantite").isEqualTo(3);


        Mockito.verify(commandeRepo, times(1)).addCommande(commande);
    }
}
