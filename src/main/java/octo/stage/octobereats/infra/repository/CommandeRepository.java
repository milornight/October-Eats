package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;

import java.util.List;

public interface CommandeRepository {
    public List<Commande> getCommandes();
    public Commande addCommande(Commande commande);
    public CommandeStatus getCommandStatus(long id);
}
