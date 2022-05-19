package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepo implements ClientRepository{

    private List<Client> list = new ArrayList<Client>();

    Client C1 = new Client("Lola");
    Client C2 = new Client("Tom");
    Client C3 = new Client("Ami");
    Client C4 = new Client("Hela");

    public ClientRepo() {
        list.add(C1);
        list.add(C2);
        list.add(C3);
        list.add(C4);
    }

    public List<Client> getClients() {
        return list;
    }
}
