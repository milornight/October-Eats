package octo.stage.octobereats.usecases.client;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
public class SuivreLesCommandesDuClient {

    CommandeFlux commandeFlux;

    public SuivreLesCommandesDuClient(CommandeFlux commandeFlux) {
        this.commandeFlux = commandeFlux;
    }

    public Publisher<Commande> exécuter(long id){
        return commandeFlux.getCommandesPublisher().filter((commande)-> commande.getIdClient() == id);
    }
}
