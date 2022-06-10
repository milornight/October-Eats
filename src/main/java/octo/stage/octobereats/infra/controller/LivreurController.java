package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.Livreur;
import octo.stage.octobereats.infra.repository.LivreurRepository;
import octo.stage.octobereats.usecases.livreur.CreerLivreur;
import octo.stage.octobereats.usecases.livreur.RecupererLesLivreurs;
import octo.stage.octobereats.usecases.livreur.SuivreCommandesDisponibles;
import octo.stage.octobereats.usecases.livreur.SuivreCommandesLivreur;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LivreurController {

    LivreurRepository livreurRepository;

    final
    RecupererLesLivreurs recupererLesLivreurs;

    final
    CreerLivreur creerLivreur;

    final
    SuivreCommandesDisponibles suivreCommandesDisponibles;

    final
    SuivreCommandesLivreur suivreCommandesLivreur;

    public LivreurController(LivreurRepository livreurRepository, RecupererLesLivreurs recupererLesLivreurs, CreerLivreur creerLivreur, SuivreCommandesDisponibles suivreCommandesDisponibles, SuivreCommandesLivreur suivreCommandesLivreur) {
         this.livreurRepository = livreurRepository;
        this.recupererLesLivreurs = recupererLesLivreurs;
        this.creerLivreur = creerLivreur;
        this.suivreCommandesDisponibles = suivreCommandesDisponibles;
        this.suivreCommandesLivreur = suivreCommandesLivreur;
    }

    // get la liste des livreurs
    @GetMapping("/livreurs")
    public List<Livreur> livreurs(){
        return recupererLesLivreurs.exécuter();
    }

    // post un nouveau livreur dans la liste
    @PostMapping("/livreurs")
    public Livreur newLivreur(@RequestBody Livreur livreur){
        return creerLivreur.exécuter(livreur);
    }

    // get la liste des commandes qui ne sont pas encore choisies par les livreurs en réactive
    @GetMapping(path="/livreurs/commandesDisponibles", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<Commande> getCommandesDisponibles(){
        return suivreCommandesDisponibles.exécuter();
    }

    // get les commandes choisies pour le livreur identifiant=id
    @GetMapping(path="/livreurs/{id}/commandes",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<Commande> getCommandeLivreur(@PathVariable long id){
        return suivreCommandesLivreur.exécuter(id);
    }

}
