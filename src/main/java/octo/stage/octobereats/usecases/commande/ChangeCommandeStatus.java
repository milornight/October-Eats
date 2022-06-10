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

    public CommandeStatus exécuter(CommandeStatus commandeStatus, long idCommande) throws RegleChangementStatusException, CommandeNePeutPasLivrerException {
        var commande = commandeRepository.findById(idCommande);
        var statusAvant = commande.getCommandeStatus();

        var nonRespecteRegleChangementStatut = (commandeStatus.ordinal() - statusAvant.ordinal()) != 1;
        if (nonRespecteRegleChangementStatut) {
            throw new RegleChangementStatusException();
        }

        var commandePrête = statusAvant == CommandeStatus.PRETE;
        var commandeIndisponible = commande.getIdLivreur()!=0;

        if(commandePrête && !commandeIndisponible){
            throw new CommandeNePeutPasLivrerException();
        }

        commandeStatus.setIdCommande(idCommande);
        commande.setCommandeStatus(commandeStatus);
        statusFlux.getStatusStream().next(commandeStatus);
        commandeFlux.getCommandesStream().next(commande);
        return commandeStatus;
    }
}
