package octo.stage.octobereats.usecases.livreur;

import octo.stage.octobereats.domain.Livreur;
import octo.stage.octobereats.infra.repository.LivreurRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecupererLesLivreurs {

    LivreurRepository livreurRepository;

    public RecupererLesLivreurs(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    public List<Livreur> exécuter(){
        return livreurRepository.getLivreurs();
    }
}
