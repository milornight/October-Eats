package octo.stage.octobereats.usecases.client;

import octo.stage.octobereats.domain.Client;
import octo.stage.octobereats.infra.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecupererLesClients {

    ClientRepository clientRepository;

    public RecupererLesClients(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> exécuter(){
        return clientRepository.getClients();
    }
}
