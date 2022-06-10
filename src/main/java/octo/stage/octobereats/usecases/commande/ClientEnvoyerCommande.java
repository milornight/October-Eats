package octo.stage.octobereats.usecases.commande;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import octo.stage.octobereats.infra.flux.StatusFlux;
import octo.stage.octobereats.infra.repository.CommandeRepository;
import org.springframework.stereotype.Component;

@Component
public class ClientEnvoyerCommande {

    CommandeRepository commandeRepository;
    CommandeFlux commandeFlux;
    StatusFlux statusFlux;

    public ClientEnvoyerCommande(CommandeRepository commandeRepository, CommandeFlux commandeFlux, StatusFlux statusFlux) {
        this.commandeRepository = commandeRepository;
        this.commandeFlux = commandeFlux;
        this.statusFlux = statusFlux;
    }

    public Commande exécuter(Commande commande){
        commandeFlux.getCommandesStream().next(commande);
        CommandeStatus status = commande.getCommandeStatus();
        long id = commande.getIdCommande();
        status.setIdCommande(id);
        statusFlux.getStatusStream().next(commande.getCommandeStatus());
        return commandeRepository.addCommande(commande);
    }

}
