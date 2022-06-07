package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.domain.Livreur;

import java.util.List;

public interface CommandeRepository {
    public List<Commande> getCommandes();
    public Commande addCommande(Commande commande);
    // public CommandeStatus changeStatus(long id,CommandeStatus status);
    public Commande findById(long id);
    public long getIdLivreur(long id);
    //public boolean checkCommandesSontLivres(List<Commande> commandeList);
}
