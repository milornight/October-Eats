package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.domain.Livreur;
import octo.stage.octobereats.domain.exception.CommandeDejaPrisException;
import octo.stage.octobereats.domain.exception.CommandePasPreteException;
import octo.stage.octobereats.domain.exception.LivreurIndisponibleException;
import octo.stage.octobereats.infra.controller.output.LivreurOutput;
import octo.stage.octobereats.infra.repository.CommandeRepository;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import octo.stage.octobereats.infra.flux.StatusFlux;
import octo.stage.octobereats.infra.repository.LivreurRepository;
import octo.stage.octobereats.usecases.commande.ChangeCommandeStatus;
import octo.stage.octobereats.usecases.commande.ClientEnvoyerCommande;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import octo.stage.octobereats.usecases.commande.LivreurChoisitCommande;

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

    @Autowired
    LivreurChoisitCommande livreurChoisitCommande;

    @Autowired
    ChangeCommandeStatus changeCommandeStatus;

    @Autowired
    ClientEnvoyerCommande clientEnvoyerCommande;

    public CommandeController(CommandeRepository commandeRepository,LivreurRepository livreurRepository) {
        this.commandeRepository = commandeRepository;
        this.livreurRepository = livreurRepository;
    }

    // get la liste des commandes
    @GetMapping("/commandes")
    public List<Commande> commandes(){
        return commandeRepository.getCommandes();
    } // todo: usecase : RecupererLesCommandes

    // post un nouveau commande dans la liste
    @PostMapping("/commandes")
    public Commande newCommande(@RequestBody Commande commande) {
        commandeFlux.getCommandesStream().next(commande);
        CommandeStatus status = commande.getCommandeStatus();
        long id = commande.getIdCommande();
        status.setIdCommande(id);
        statusFlux.getStatusStream().next(commande.getCommandeStatus());
        return commandeRepository.addCommande(commande);

        //return clientEnvoyerCommande.exécuter(commande);
    } // todo: usecase : CreerCommande

    // put le nouveau status du commande qui a identifiant = id
    @PutMapping("/commandes/{id}/status")
    public CommandeStatus newStatus(@RequestBody CommandeStatus status, @PathVariable long id){

       // CommandeStatus status = statusRepository.convertir(body);
        //CommandeStatus status = (CommandeStatus) body;
        status.setIdCommande(id);
        CommandeStatus commandeStatus = commandeRepository.changeStatus(id,status);
        statusFlux.getStatusStream().next(commandeStatus);
        Commande commande = commandeRepository.findById(commandeStatus.getIdCommande());
        commandeFlux.getCommandesStream().next(commande);
        return commandeStatus;

        //return changeCommandeStatus.exécuter(status,id);
    }

    // get la status du commandes qui a identifiant = id
    @GetMapping(path="/commandes/{id}/status", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<CommandeStatus> getStatus(@PathVariable long id) {
        return statusFlux.getStatusPublisher().filter((status) ->  status.getIdCommande() == id);
    } // todo: usecase : SuivreStatusCommande

    // put le livreur pour prend charge du commande identifiant=id
    @PutMapping("/commandes/{id}/livreur")
    public ResponseEntity choisirCommande(@RequestBody long idLivreur, @PathVariable long id){
        try {
            Livreur livreur = livreurChoisitCommande.exécuter(idLivreur,id);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(new LivreurOutput(livreur));

        } catch (CommandePasPreteException |LivreurIndisponibleException | CommandeDejaPrisException e) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getMessage());
        }
    }


    // get les informations du livreur qui prend charge le commande identifiant=id
    @GetMapping("/commandes/{id}/livreur")
    public LivreurOutput getCommandeLivreur(@PathVariable long id){
        long idLivreur = commandeRepository.getIdLivreur(id);
        Livreur livreur = livreurRepository.findById(idLivreur);
        return new LivreurOutput(livreur);
    }// todo: usecase : RecupererLeLivreurDeLaCommande

}
