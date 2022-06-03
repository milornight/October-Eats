package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
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
    public CommandeStatus changeStatus(long id,CommandeStatus statusApres){
        for(Commande commande:list) {
            if (id == commande.getIdCommande()) {
                CommandeStatus statusAvant = commande.getCommandeStatus();
                if(statusApres.ordinal()-statusAvant.ordinal()==1){
                    if((statusAvant==CommandeStatus.PRETE && commande.getIdLivreur()!=0) || statusAvant!=CommandeStatus.PRETE){
                        commande.setCommandeStatus(statusApres);
                        return statusApres;
                    }
                }
            }
        }
        System.out.println("Le changement du status de commande n'a pas respecté la règle");
        return null;
    }

    // trouver une commande dans la liste à partir son identifiant
    public Commande findById(long id){
        for(Commande commande:list) {
            if (id == commande.getIdCommande()) {
                return commande;
            }
        }
        return null;
    }

    // récupérer identifiant du livreur pour commande(identifiant = id)
    public long getIdLivreur(long id){
        for(Commande commande:list) {
            if (id == commande.getIdCommande()) {
                return commande.getIdLivreur();
            }
        }
        return 0;
    }

    public boolean checkCommandesSontLivres(List<Commande> commandeList){
        for(Commande commande:commandeList) {
            if (commande.getCommandeStatus() != CommandeStatus.LIVREE) {
                return false;
            }
        }
        return true;
    }

}
