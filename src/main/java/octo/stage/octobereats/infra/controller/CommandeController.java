package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.Restaurant;
import octo.stage.octobereats.infra.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class CommandeController {

    @Autowired
    CommandeRepository commandeRepository;

    public CommandeController(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @GetMapping("/commandes")
    public List<Commande> commandes(){
        return commandeRepository.getCommandes();
    }


    @GetMapping("/restaurants/{id}/commandes")
    @ResponseBody
    public List<Commande> GetCommandeRestaurant(@PathVariable long id) {

        return commandeRepository.findById(id);
    }

    @PostMapping("/commandes")
    public Commande newCommande(@RequestBody Commande commande) {
        System.out.println(commande);
        return commandeRepository.addCommande(commande);
    }

}
