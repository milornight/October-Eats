package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Commande;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommandeRepo implements CommandeRepository{

    private final List<Commande> list = new ArrayList<>();

    // récupérer la liste des commandes
    public List<Commande> getCommandes() {
        return list;
    }

    // ajouter un nouveau commande dans la liste
    public Commande addCommande(Commande commande){
        list.add(commande);
        return commande;
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
        Commande commande = findById(id);
        return commande.getIdLivreur();
    }

}
