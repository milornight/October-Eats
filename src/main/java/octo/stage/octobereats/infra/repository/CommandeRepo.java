package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Commande;
import octo.stage.octobereats.domain.CommandeStatus;
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

    public CommandeStatus getCommandStatus(long id){
        for(Commande commande:list){
            if(id == commande.getIdCommande()){
                return commande.getCommandeStatus();
            }
        }
        return null;
    }

}
