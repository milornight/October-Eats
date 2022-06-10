package octo.stage.octobereats.usecases.commande;

import octo.stage.octobereats.domain.CommandeStatus;
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

    public CommandeStatus exécuter(CommandeStatus commandeStatus, long idCommande){
        commandeStatus.setIdCommande(idCommande);
        var commande = commandeRepository.findById(idCommande);
        var statusAvant = commande.getCommandeStatus();

        var respecteRegleStatut = (commandeStatus.ordinal() - statusAvant.ordinal()) == 1;

        var commandePrête = statusAvant==CommandeStatus.PRETE;
        var commandeDejaChoisit = commande.getIdLivreur()!=0;

        if (respecteRegleStatut){
            if((commandePrête && commandeDejaChoisit) || !commandePrête){
                commande.setCommandeStatus(commandeStatus);
                statusFlux.getStatusStream().next(commandeStatus);
                commandeFlux.getCommandesStream().next(commandeRepository.findById(commandeStatus.getIdCommande()));
                return commandeStatus;
            }
        }
        System.out.println("Le changement du status de commande n'a pas respecté la règle");
        return null;
    }
}
