package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.domain.Livreur;
import octo.stage.octobereats.infra.repository.CommandeRepository;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import octo.stage.octobereats.infra.flux.StatusFlux;
import octo.stage.octobereats.infra.repository.LivreurRepository;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import octo.stage.octobereats.usecases.livreurChoisitCommande;

import java.util.List;

// un controller sert à recevoir des arguments, les donner à un usecase (domain)
// et retourne le résultat du usecase en tant que réponse
// un controller n'a pas le droit d'appeler un repository

@RestController
public class CommandeController {

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    CommandeFlux commandeFlux;

    @Autowired
    StatusFlux statusFlux;

    @Autowired
    LivreurRepository livreurRepository;

    livreurChoisitCommande livreurChoisitCommande;

    public CommandeController(CommandeRepository commandeRepository,LivreurRepository livreurRepository) {
        this.commandeRepository = commandeRepository;
        this.livreurRepository = livreurRepository;
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
        Commande commande = commandeRepository.findById(commandeStatus.getIdCommande());
        commandeFlux.getCommandesStream().next(commande);
        return commandeStatus;
    }

    // get la status du commandes qui a identifiant = id
    @GetMapping(path="/commandes/{id}/status", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<CommandeStatus> getStatus(@PathVariable long id) {
        return statusFlux.getStatusPublisher().filter((status) ->  status.getIdCommande() == id);
    }

    // put le livreur pour prend charge du commande identifiant=id
    @PutMapping("/commandes/{id}/livreur")
    public Livreur choisirCommande(@RequestBody long idLivreur, @PathVariable long id){
        Commande commande = commandeRepository.findById(id);
        List<Livreur> list = livreurRepository.getLivreurs();
        List<Commande> commandeList;
        for(Livreur livreur:list){
            if(idLivreur == livreur.getId()){
                commandeList = livreur.getCommandeList();
                if(commandeRepository.checkCommandesSontLivres(commandeList) && commande.getCommandeStatus()==CommandeStatus.PRETE){
                    commande.setIdLivreur(idLivreur);
                    livreurRepository.addCommandeDansList(commande,livreur);
                    commandeFlux.getCommandesStream().next(commandeRepository.findById(id));
                    return livreur;
                }
            }
        }
        return null;

        //return livreurChoisitCommande.exécuter(idLivreur,id);
    }



    // get les informations du livreur qui prend charge le commande identifiant=id
    @GetMapping("/commandes/{id}/livreur")
    public Livreur getCommandeLivreur(@PathVariable long id){
        long idLivreur = commandeRepository.getIdLivreur(id);
        List<Livreur> list = livreurRepository.getLivreurs();
        for(Livreur livreur:list){
            if(idLivreur == livreur.getId()){
                return livreur;
            }
        }
        return null;
    }

}
