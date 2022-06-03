package octo.stage.octobereats.usecases;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.infra.flux.CommandeFlux;
import octo.stage.octobereats.infra.flux.StatusFlux;
import octo.stage.octobereats.infra.repository.CommandeRepository;
import octo.stage.octobereats.infra.repository.LivreurRepository;
import org.springframework.stereotype.Component;

/*@Component
public class changeCommandeStatus {

    CommandeRepository commandeRepository;
    CommandeStatus commandeStatus;
    StatusFlux statusFlux;

    public changeCommandeStatus(CommandeRepository commandeRepository, CommandeStatus commandeStatus, StatusFlux statusFlux) {
        this.commandeRepository = commandeRepository;
        this.commandeStatus = commandeStatus;
        this.statusFlux = statusFlux;
    }

    public CommandeStatus exécuter(CommandeStatus commandeStatus, long idCommande){
      var commande = commandeRepository.findById(idCommande);
      var statusAvant = commande.getCommandeStatus();

      var respecteRegleStatut = (commandeStatus.ordinal() - statusAvant.ordinal()) == 1;

      var commandePrête = statusAvant==CommandeStatus.PRETE;
      var commandeDejaChoisit = commande.getIdLivreur()!=0;

      if(commandePrête && commandeDejaChoisit || !commandePrête){
          commande.setCommandeStatus(commandeStatus);
          return commandeStatus;
      }

      -----------------------

        System.out.println("Le changement du status de commande n'a pas respecté la règle");
        return null;

        CommandeStatus commandeStatus = commandeRepository.changeStatus(id,status);
        statusFlux.getStatusStream().next(commandeStatus);
        Commande commande = commandeRepository.findById(commandeStatus.getIdCommande());
        commandeFlux.getCommandesStream().next(commande);
    }
}*/
