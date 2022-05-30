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

    public List<Commande> getCommandes() {
        return list;
    }

    public Commande addCommande(Commande commande){
        list.add(commande);
        System.out.println("Commande crée : " + commande);
        System.out.println("Liste de commandes : " + list);
        return commande;
    }

    public CommandeStatus getCommandStatus(long id){
        for(Commande commande:list){
            if(id == commande.getIdCommande()){
                return commande.getCommandeStatus();
            }
        }
        return null;
    }

    public CommandeStatus changeStatus(long id,CommandeStatus status){
        for(Commande commande:list) {
            if (id == commande.getIdCommande()) {
                commande.setCommandeStatus(status);
                return status;
            }
        }
        return null;
    }

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
    }

}
