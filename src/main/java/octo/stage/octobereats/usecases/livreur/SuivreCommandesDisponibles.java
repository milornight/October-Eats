package octo.stage.octobereats.usecases.livreur;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
public class SuivreCommandesDisponibles {

    CommandeFlux commandeFlux;

    public SuivreCommandesDisponibles(CommandeFlux commandeFlux) {
        this.commandeFlux = commandeFlux;
    }

    public Publisher<Commande> exécuter() {
        return commandeFlux.getCommandesPublisher().filter(commande ->
                commande.getCommandeStatus() != CommandeStatus.EN_LIVRAISON &&
                        commande.getCommandeStatus() != CommandeStatus.LIVREE &&
                        commande.getIdLivreur() == 0);
    }
}
