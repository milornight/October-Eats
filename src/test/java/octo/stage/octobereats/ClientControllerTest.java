package octo.stage.octobereats;

import octo.stage.octobereats.domain.Client;
import octo.stage.octobereats.infra.controller.ClientController;
import octo.stage.octobereats.infra.repository.ClientRepo;
import octo.stage.octobereats.infra.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ClientController.class)
@Import(ClientRepo.class)
public class ClientControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    ClientRepository clientRepo;

    @Test
    public void testGetClients(){
        List<Client> listClient = List.of(new Client("Tim"),new Client("Tony"));

        Mockito.when(clientRepo.getClients()).thenReturn(listClient);

        webClient.get()
                .uri("/clients")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo(1)
                .jsonPath("$.[0].nom").isEqualTo("Tim")
                .jsonPath("$.[1].id").isEqualTo(2)
                .jsonPath("$.[1].nom").isEqualTo("Tony");

        Mockito.verify(clientRepo, times(1)).getClients();
    }
}
