package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
import octo.stage.octobereats.domain.Livreur;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommandeRepo implements CommandeRepository{

    private List<Commande> list = new ArrayList<Commande>();

    // récupérer la liste des commandes
    public List<Commande> getCommandes() {
        return list;
    }

    // ajouter un nouveau commande dans la liste
    public Commande addCommande(Commande commande){
        list.add(commande);
        return commande;
    }

    // changer la status du commande qui a identifiant = id
    public CommandeStatus changeStatus(long id,CommandeStatus status){
        for(Commande commande:list) {
            if (id == commande.getIdCommande()) {
                commande.setCommandeStatus(status);
                return status;
            }
        }
        return null;
    }

    /*
    public Livreur prendCommande(long id, Livreur livreur){
        for(Commande commande:list) {
            if (id == commande.getIdCommande()) {
                commande.setIdLivreur(livreur.getId());
                return livreur;
            }
        }
        return null;
    }

    public long getlivreur(long id){
        for(Commande commande:list) {
            if (id == commande.getIdCommande()) {
                return commande.getIdLivreur();
            }
        }
        return 0;
    }*/

}
