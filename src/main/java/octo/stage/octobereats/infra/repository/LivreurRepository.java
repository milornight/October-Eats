package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.Livreur;

import java.util.List;

public interface LivreurRepository {
    public List<Livreur> getLivreurs();
    public Livreur addLivreur(Livreur livreur);
}
