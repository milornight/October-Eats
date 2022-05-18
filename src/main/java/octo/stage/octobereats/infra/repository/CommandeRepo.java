package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Commande;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

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
        return commande;
    }

    public List<Commande> findById(long id){
        List<Commande> listRestaurantCommande = new ArrayList<Commande>();
        for(Commande commande: list){
            if(id == commande.getIdRestaurant()){
                listRestaurantCommande.add(commande);
            }
        }
        return listRestaurantCommande;
    }

}
