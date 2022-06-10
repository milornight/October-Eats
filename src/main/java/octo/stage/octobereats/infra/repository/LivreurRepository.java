package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.Livreur;

import java.util.List;

public interface LivreurRepository {
    List<Livreur> getLivreurs();
    Livreur addLivreur(Livreur livreur);
    void addCommandeDansList(Commande commande, Livreur livreur);
    Livreur findById(long id);
}
