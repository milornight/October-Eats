package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Client;
import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.infra.repository.ClientRepository;
import octo.stage.octobereats.usecases.client.CreerUnClient;
import octo.stage.octobereats.usecases.client.RecupererLesClients;
import octo.stage.octobereats.usecases.client.SuivreLesCommandesDuClient;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    ClientRepository clientRepository;

    final
    RecupererLesClients recupererLesClients;

    final
    CreerUnClient creerUnClient;

    final
    SuivreLesCommandesDuClient suivreLesCommandesDuClient;

    public ClientController(ClientRepository clientRepository, RecupererLesClients recupererLesClients, CreerUnClient creerUnClient, SuivreLesCommandesDuClient suivreLesCommandesDuClient) {
        this.clientRepository = clientRepository;
        this.recupererLesClients = recupererLesClients;
        this.creerUnClient = creerUnClient;
        this.suivreLesCommandesDuClient = suivreLesCommandesDuClient;
    }

    // get la liste des clients
    @GetMapping("/clients")
    public List<Client> clients(){
        return recupererLesClients.exécuter();
    }

    // post un nouveau client dans la liste
    @PostMapping("/clients")
    public Client newClient(@RequestBody Client client){
        return creerUnClient.exécuter(client);
    }

    // get la list des commandes lié au client qui a identifiant = id en réactive
    @GetMapping(path="/clients/{id}/commandes", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<Commande> getCommandeClient(@PathVariable long id) {
        return suivreLesCommandesDuClient.exécuter(id);
    }
}
