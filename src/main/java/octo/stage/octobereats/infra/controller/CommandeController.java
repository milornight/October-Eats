package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.Plat;
//import octo.stage.octobereats.domain.PlatCommande;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeController {

    @PostMapping("/commandes")
    public String newCommande(@RequestBody Commande commande) {
        System.out.println(commande.toString());
        return commande.toString();
    }

}
