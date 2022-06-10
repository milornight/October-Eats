package octo.stage.octobereats.usecases.client;

import octo.stage.octobereats.domain.Client;
import octo.stage.octobereats.infra.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class CreerUnClient {

    ClientRepository clientRepository;

    public CreerUnClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client exécuter(Client client){
        return clientRepository.addClient(client);
    }
}
