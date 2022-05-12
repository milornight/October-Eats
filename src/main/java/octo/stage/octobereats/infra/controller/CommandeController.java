package octo.stage.octobereats.infra.controller;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.Plat;
import octo.stage.octobereats.domain.PlatCommande;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeController {

    @PostMapping("/commandes")
    public Commande newCommande(@RequestBody long idRestaurant, Plat plat, int quantite) {
        System.out.println("11111111111");
        PlatCommande platCommande = new PlatCommande(plat,quantite);
        System.out.println("222222222222");
        List<PlatCommande> listPlatCommande = List.of(platCommande);
        System.out.println("3333333333333");
        Commande commande = new Commande(idRestaurant,listPlatCommande);
        System.out.println(commande);
        return commande;
    }

}
