package octo.stage.octobereats.usecases.commande;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.infra.repository.CommandeRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecupererLesCommandes {

    CommandeRepository commandeRepository;

    public RecupererLesCommandes(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> exécuter(){
        return commandeRepository.getCommandes();
    }
}
