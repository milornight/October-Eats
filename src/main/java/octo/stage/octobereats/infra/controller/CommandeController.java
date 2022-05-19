package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.infra.repository.CommandeRepository;
import octo.stage.octobereats.infra.CommandeFlux;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;

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

    /*@GetMapping("/restaurants/{id}/commandes")
    public List<Commande> CommandeRestaurant(@PathVariable long id) {
        return commandeRepository.findById(id);
    }*/

    /*@GetMapping("/restaurants/{id}/commandes")
    @ResponseBody
    public Mono<Commande> GetCommandeRestaurant(@PathVariable long id) {
        List<Commande> listCommande = commandeRepository.findById(id);
        return WebClient.create("http://localhost:8080")
                .get()
                .uri("/restaurants/{id}/commandes",id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Commande.class)
                .log();
    }*/

   /* @GetMapping(path="/restaurants/{id}/commandes", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Commande> CommandeRestaurant(@PathVariable long id) {
        return Flux.interval(Duration.ofSeconds(1))
                .fromIterable(commandeRepository.findById(id))
                .log();
    }*/

    @GetMapping(path="/restaurants/{id}/commandes", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<Commande> GetCommandeRestaurant(@PathVariable long id) {
        return commandeFlux.getCommandesPublisher().filter((commande)-> commande.getIdRestaurant() == id);
    }

    @PostMapping("/commandes")
    public Commande newCommande(@RequestBody Commande commande) {
        System.out.println(commande);
        commandeFlux.getCommandesStream().next(commande);
        return commandeRepository.addCommande(commande);
    }

}
