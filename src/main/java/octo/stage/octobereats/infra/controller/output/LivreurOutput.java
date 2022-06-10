package octo.stage.octobereats.infra.controller.output;

import octo.stage.octobereats.domain.Livreur;

public class LivreurOutput {

    private final long id;
    private final String nom;
    private final String prenom;

    public LivreurOutput(Livreur livreur) {
        this.id = livreur.getId();
        this.nom = livreur.getNom();
        this.prenom = livreur.getPrenom();
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
