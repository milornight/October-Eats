package octo.stage.octobereats.usecases.restaurant;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
public class SuivreLesCommandesDuRestaurant {

    CommandeFlux commandeFlux;

    public SuivreLesCommandesDuRestaurant(CommandeFlux commandeFlux) {
        this.commandeFlux = commandeFlux;
    }

    public Publisher<Commande> exécuter(long id){
        return commandeFlux.getCommandesPublisher().filter((commande)-> commande.getIdRestaurant() == id);
    }
}
