package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.Restaurant;
import octo.stage.octobereats.infra.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
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

    @GetMapping(path="/restaurants/{id}/commandes", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Commande> CommandeRestaurant(@PathVariable long id) {
        return Flux.interval(Duration.ofSeconds(1))
                .fromIterable(commandeRepository.findById(id))
                .log();
    }

    @PostMapping("/commandes")
    public Commande newCommande(@RequestBody Commande commande) {
        System.out.println(commande);
        return commandeRepository.addCommande(commande);
    }

}
