package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Livreur;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LivreurRepo implements LivreurRepository{

    private List<Livreur> list = new ArrayList<>();

    public List<Livreur> getLivreurs(){
        return list;
    }

    public Livreur addLivreur(Livreur livreur){
        list.add(livreur);
        return livreur;
    }
}
