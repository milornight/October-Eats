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

import java.util.List;

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

    // get la liste des commandes
    @GetMapping("/commandes")
    public List<Commande> commandes(){
        return commandeRepository.getCommandes();
    }

    // post un nouveau commande dans la liste
    @PostMapping("/commandes")
    public Commande newCommande(@RequestBody Commande commande) {
        commandeFlux.getCommandesStream().next(commande);
        CommandeStatus status = commande.getCommandeStatus();
        long id = commande.getIdCommande();
        status.setIdCommande(id);
        statusFlux.getStatusStream().next(commande.getCommandeStatus());
        return commandeRepository.addCommande(commande);
    }

    // put le nouveau status du commande qui a identifiant = id
    @PutMapping("/commandes/{id}/status")
    public CommandeStatus newStatus(@RequestBody CommandeStatus status, @PathVariable long id){
        status.setIdCommande(id);
        CommandeStatus commandeStatus = commandeRepository.changeStatus(id,status);
        statusFlux.getStatusStream().next(commandeStatus);
        return commandeStatus;
    }

    // get la status du commandes qui a identifiant = id
    @GetMapping(path="/commandes/{id}/status", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<CommandeStatus> getStatus(@PathVariable long id) {
        return statusFlux.getStatusPublisher().filter((status) ->  {
            System.out.println(id + " == " + status.getIdCommande() + " = " + (status.getIdCommande() == id));
            return status.getIdCommande() == id;
        });
    }

    /*-------------------------------------------*/

    @GetMapping("/commandes/{id}/livreur")
    public long getCommandeLivreur(@PathVariable long id){
        return commandeRepository.getlivreur(id);
    }

    @PutMapping("/commandes/{id}/livreur")
    public Livreur choisirCommande(@RequestBody Livreur livreur, @PathVariable long id){
        return commandeRepository.prendCommande(id, livreur);
    }

}
