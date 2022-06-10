package octo.stage.octobereats.usecases.commande;

import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.infra.flux.StatusFlux;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
public class SuivreStatusCommande {

    StatusFlux statusFlux;

    public SuivreStatusCommande(StatusFlux statusFlux) {
        this.statusFlux = statusFlux;
    }

    public Publisher<CommandeStatus> exécuter(long id){
        return statusFlux.getStatusPublisher().filter((status) -> status.getIdCommande() == id);
    }
}
