package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.infra.repository.CommandeRepository;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import octo.stage.octobereats.infra.flux.StatusFlux;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collector;

@RestController
public class CommandeController {

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    CommandeFlux commandeFlux;

    @Autowired
    StatusFlux statusFlux;

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

   /*@GetMapping("/commandes/{id}/status")
    public Publisher<Commande> GetStatus(@PathVariable long id) {
        Publisher<Commande> commandePub = (commandeFlux.getCommandesPublisher().filter((commande)-> (commande.getIdClient() == id)));
        System.out.println("AAAAAAAAAAAA " + commandePub);
        return commandePub;
        //statusFlux.getStatusPublisher().filter(status -> status.getIdCommande()==id);
        //Flux<Commande> commandePub = commandeFlux.getCommandesPublisher();
        //System.out.println("AAAAAAAAAAAA " + commandePub);
        //Mono<List<Commande>> commandeList = commandeFlux.getCommandesPublisher().collectList();
        //System.out.println("BBBBBBBBBBB "+ commandeList);
        /*for (Commande commande : commandeList) {
            if (commande.getIdCommande() == id) {
                return commande.getCommandeStatus();
            }
        }
        //return null;
    }*/

    /*@GetMapping("/commandes/{id}/status")
    public Publisher<CommandeStatus> GetStatus(@PathVariable long id) {
        return statusFlux.getStatusPublisher().filter((status) -> status.getIdCommande()==id);
    }*/

    @PostMapping("/commandes")
    public Commande newCommande(@RequestBody Commande commande) {
        //System.out.println(commande);
        commandeFlux.getCommandesStream().next(commande);
        statusFlux.getStatusStream().next(commande.getCommandeStatus());
        return commandeRepository.addCommande(commande);
    }

    @PutMapping("/commandes/{id}/status")
    public CommandeStatus newStatus(@RequestBody CommandeStatus status, @PathVariable long id){
        //System.out.println(status);
        statusFlux.getStatusStream().next(status);
        CommandeStatus commandeStatus= commandeRepository.changeStatus(id,status);
        //System.out.println(status);
        return commandeStatus;
    }

}
