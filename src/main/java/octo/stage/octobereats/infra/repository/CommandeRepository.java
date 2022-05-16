package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Commande;

import java.util.List;

public interface CommandeRepository {
    public List<Commande> getCommandes();
    public String addCommande(Commande commande);
}
