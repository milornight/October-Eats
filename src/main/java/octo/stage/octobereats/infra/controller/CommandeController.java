package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandeController {

    @PostMapping("/commandes")
    public String newCommande(@RequestBody Commande commande) {
        System.out.println(commande.toString());
        return commande.toString();
    }

}
