package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Client;

import java.util.List;

public interface ClientRepository {
    List<Client> getClients();
    Client addClient(Client client);
}
