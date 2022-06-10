package octo.stage.octobereats.usecases.commande;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import octo.stage.octobereats.infra.flux.StatusFlux;
import octo.stage.octobereats.infra.repository.CommandeRepository;
import org.springframework.stereotype.Component;

@Component
public class CreerCommande {

    CommandeRepository commandeRepository;
    CommandeFlux commandeFlux;
    StatusFlux statusFlux;

    public CreerCommande(CommandeRepository commandeRepository, CommandeFlux commandeFlux, StatusFlux statusFlux) {
        this.commandeRepository = commandeRepository;
        this.commandeFlux = commandeFlux;
        this.statusFlux = statusFlux;
    }

    public Commande exécuter(Commande commande){
        commandeFlux.getCommandesStream().next(commande);
        CommandeStatus status = commande.getCommandeStatus();
        statusFlux.getStatusStream().next(status);
        return commandeRepository.addCommande(commande);
    }

}
