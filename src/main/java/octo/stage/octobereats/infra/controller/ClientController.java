package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Client;
import octo.stage.octobereats.infra.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients")
    public List<Client> clients(){
        return clientRepository.getClients();
    }

    @PostMapping("/clients")
    public Client newClient(@RequestBody Client client){
        return clientRepository.addClient(client);
    }
}
