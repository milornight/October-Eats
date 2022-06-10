package octo.stage.octobereats.usecases.livreur;

import octo.stage.octobereats.domain.Livreur;
import octo.stage.octobereats.infra.repository.LivreurRepository;
import org.springframework.stereotype.Component;

@Component
public class CreerLivreur {

    LivreurRepository livreurRepository;

    public CreerLivreur(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    public Livreur exécuter(Livreur livreur){
        return livreurRepository.addLivreur(livreur);
    }
}
