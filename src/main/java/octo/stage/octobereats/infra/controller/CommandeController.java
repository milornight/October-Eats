package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.domain.Livreur;
import octo.stage.octobereats.domain.exception.*;
import octo.stage.octobereats.infra.controller.output.LivreurOutput;
import octo.stage.octobereats.usecases.commande.*;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// un controller sert à recevoir des arguments, les donner à un usecase (domain)
// et retourne le résultat du usecase en tant que réponse
// un controller n'a pas le droit d'appeler un repository

@RestController
public class CommandeController {

    final
    RecupererLesCommandes recupererLesCommandes;

    final
    CreerCommande creerCommande;

    final
    ChangeCommandeStatus changeCommandeStatus;

    final
    SuivreStatusCommande suivreStatusCommande;

    final
    LivreurChoisitCommande livreurChoisitCommande;

    final
    RecupererLeLivreurDeLaCommande recupererLeLivreurDeLaCommande;

    public CommandeController(RecupererLesCommandes recupererLesCommandes, CreerCommande creerCommande, ChangeCommandeStatus changeCommandeStatus, SuivreStatusCommande suivreStatusCommande, LivreurChoisitCommande livreurChoisitCommande, RecupererLeLivreurDeLaCommande recupererLeLivreurDeLaCommande) {
        this.recupererLesCommandes = recupererLesCommandes;
        this.creerCommande = creerCommande;
        this.changeCommandeStatus = changeCommandeStatus;
        this.suivreStatusCommande = suivreStatusCommande;
        this.livreurChoisitCommande = livreurChoisitCommande;
        this.recupererLeLivreurDeLaCommande = recupererLeLivreurDeLaCommande;
    }

    // get la liste des commandes
    @GetMapping("/commandes")
    public List<Commande> commandes(){
        return recupererLesCommandes.exécuter();
    }

    // post un nouveau commande dans la liste
    @PostMapping("/commandes")
    public Commande newCommande(@RequestBody Commande commande) {
        return creerCommande.exécuter(commande);
    }

    // put le nouveau status du commande qui a identifiant = id
    @PutMapping("/commandes/{id}/status")
    public ResponseEntity newStatus(@RequestBody CommandeStatus status, @PathVariable long id){
        try{
            CommandeStatus commandeStatus = changeCommandeStatus.exécuter(status,id);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(commandeStatus);
        } catch (RegleChangementStatusException |CommandeNePeutPasLivrerException e){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getMessage());
        }
    }

    // get la status du commandes qui a identifiant = id
    @GetMapping(path="/commandes/{id}/status", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<CommandeStatus> getStatus(@PathVariable long id) {
        return suivreStatusCommande.exécuter(id);
    }

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


    // get les information du livreur qui prend charge le commande identifiant=id
    @GetMapping("/commandes/{id}/livreur")
    public LivreurOutput getCommandeLivreur(@PathVariable long id){
        return recupererLeLivreurDeLaCommande.exécuter(id);
    }

}
