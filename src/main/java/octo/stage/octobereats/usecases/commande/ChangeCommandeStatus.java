package octo.stage.octobereats.usecases.commande;

import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.domain.exception.CommandeNePeutPasLivrerException;
import octo.stage.octobereats.domain.exception.RegleChangementStatusException;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import octo.stage.octobereats.infra.flux.StatusFlux;
import octo.stage.octobereats.infra.repository.CommandeRepository;
import org.springframework.stereotype.Component;

@Component
public class ChangeCommandeStatus {

    CommandeRepository commandeRepository;
    CommandeFlux commandeFlux;
    StatusFlux statusFlux;

    public ChangeCommandeStatus(CommandeRepository commandeRepository, CommandeFlux commandeFlux, StatusFlux statusFlux) {
        this.commandeRepository = commandeRepository;
        this.commandeFlux = commandeFlux;
        this.statusFlux = statusFlux;
    }

    public CommandeStatus exécuter(CommandeStatus nouveauStatus, long idCommande) throws RegleChangementStatusException, CommandeNePeutPasLivrerException {
        var commande = commandeRepository.findById(idCommande);

        commande.vérifierPeutChangerDeStatus(nouveauStatus);
        nouveauStatus.setIdCommande(idCommande);
        commande.setCommandeStatus(nouveauStatus);
        statusFlux.getStatusStream().next(nouveauStatus);
        commandeFlux.getCommandesStream().next(commande);
        return nouveauStatus;
    }
}

