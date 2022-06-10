package octo.stage.octobereats.usecases.commande;

import octo.stage.octobereats.domain.Livreur;
import octo.stage.octobereats.infra.controller.output.LivreurOutput;
import octo.stage.octobereats.infra.repository.CommandeRepository;
import octo.stage.octobereats.infra.repository.LivreurRepository;
import org.springframework.stereotype.Component;

@Component
public class RecupererLeLivreurDeLaCommande {

    CommandeRepository commandeRepository;
    LivreurRepository livreurRepository;

    public RecupererLeLivreurDeLaCommande(CommandeRepository commandeRepository, LivreurRepository livreurRepository) {
        this.commandeRepository = commandeRepository;
        this.livreurRepository = livreurRepository;
    }

    public LivreurOutput exécuter(long idCommande){
        long idLivreur = commandeRepository.getIdLivreur(idCommande);
        Livreur livreur = livreurRepository.findById(idLivreur);
        return new LivreurOutput(livreur);
    }
}
