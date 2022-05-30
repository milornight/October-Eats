package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.domain.Livreur;
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
    public Publisher<Commande> getCommandeRestaurant(@PathVariable long id) {
        return commandeFlux.getCommandesPublisher().filter((commande)-> commande.getIdRestaurant() == id);
    }

    @GetMapping(path="/clients/{id}/commandes", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<Commande> getCommandeClient(@PathVariable long id) {
        return commandeFlux.getCommandesPublisher().filter((commande)-> commande.getIdClient() == id);
    }

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
        status.setIdCommande(id);
        CommandeStatus commandeStatus = commandeRepository.changeStatus(id,status);
        statusFlux.getStatusStream().next(commandeStatus);
        //System.out.println(status);
        return commandeStatus;
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

    @GetMapping(path="/commandes/{id}/status", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<CommandeStatus> GetStatus(@PathVariable long id) {
        return statusFlux.getStatusPublisher().filter((status) ->  {
            System.out.println(id + " == " + status.getIdCommande() + " = " + (status.getIdCommande() == id));
            return status.getIdCommande() == id;
        });
    }

    //    @GetMapping("/commandes/{id}/status")
    //    public CommandeStatus getStatus(@PathVariable long id){
    //        return commandeRepository.getCommandStatus(id);
    //    }

    @GetMapping(path="/livreurs/commandesPasEncoreChoisies", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<Commande> getCommandesPasEncoreChoisies(){
        return commandeFlux.getCommandesPublisher().filter(commande ->
                commande.getCommandeStatus() == CommandeStatus.RECUE ||
                        commande.getCommandeStatus() == CommandeStatus.EN_PREPARATION &&
                                commande.getIdLivreur() == 0);
    }

    @GetMapping("/commandes/{id}/livreur")
    public long getCommandeLivreur(@PathVariable long id){
        return commandeRepository.getlivreur(id);
    }

    @PutMapping("/commandes/{id}/livreur")
    public Livreur choisirCommande(@RequestBody Livreur livreur, @PathVariable long id){
        return commandeRepository.prendCommande(id, livreur);
    }

}
