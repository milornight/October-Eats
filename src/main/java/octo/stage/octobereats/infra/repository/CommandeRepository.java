package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Commande;

import java.util.List;

public interface CommandeRepository {
    List<Commande> getCommandes();
    Commande addCommande(Commande commande);
    Commande findById(long id);
    long getIdLivreur(long id);
}
