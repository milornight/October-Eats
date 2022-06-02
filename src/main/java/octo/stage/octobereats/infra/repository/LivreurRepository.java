package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.Livreur;

import java.util.List;

public interface LivreurRepository {
    public List<Livreur> getLivreurs();
    public Livreur addLivreur(Livreur livreur);
    public Commande addCommandeDansList(Commande commande,Livreur livreur);
    public Livreur findById(long id);
}
