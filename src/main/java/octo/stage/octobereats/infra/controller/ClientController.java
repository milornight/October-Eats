package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Client;
import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import octo.stage.octobereats.infra.repository.ClientRepository;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    ClientRepository clientRepository;

    @Autowired
    CommandeFlux commandeFlux;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // get la liste des clients
    @GetMapping("/clients")
    public List<Client> clients(){
        return clientRepository.getClients();
    } //todo : usecase : RecupererLesClients

    // post un nouveau client dans la liste
    @PostMapping("/clients")
    public Client newClient(@RequestBody Client client){
        return clientRepository.addClient(client);
    } //todo usecase : CreerUnClient

    // get la list des commandes lié au client qui a identifiant = id en réactive
    @GetMapping(path="/clients/{id}/commandes", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<Commande> getCommandeClient(@PathVariable long id) {
        return commandeFlux.getCommandesPublisher().filter((commande)-> commande.getIdClient() == id);
    } //todo usecase : SuivreLesCommandesDuClient
}
