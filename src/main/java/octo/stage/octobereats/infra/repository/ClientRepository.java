package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Client;

import java.util.List;

public interface ClientRepository {
    public List<Client> getClients();
}
