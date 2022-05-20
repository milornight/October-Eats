package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.infra.repository.CommandeRepository;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommandeController {

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    CommandeFlux commandeFlux;

    public CommandeController(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @GetMapping("/commandes")
    public List<Commande> commandes(){
        return commandeRepository.getCommandes();
    }

    @GetMapping(path="/restaurants/{id}/commandes", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<Commande> GetCommandeRestaurant(@PathVariable long id) {
        return commandeFlux.getCommandesPublisher().filter((commande)-> commande.getIdRestaurant() == id);
    }

    @GetMapping(path="/clients/{id}/commandes", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<Commande> GetCommandeClient(@PathVariable long id) {
        return commandeFlux.getCommandesPublisher().filter((commande)-> commande.getIdClient() == id);
    }

    @GetMapping("/commandes/{id}/status")
    public CommandeStatus GetStatus(@PathVariable long id){
        return commandeRepository.getCommandStatus(id);
    }

    @PostMapping("/commandes")
    public Commande newCommande(@RequestBody Commande commande) {
        System.out.println(commande);
        commandeFlux.getCommandesStream().next(commande);
        return commandeRepository.addCommande(commande);
    }

}
