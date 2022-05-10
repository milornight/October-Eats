package octo.stage.octobereats;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Restaurant {

    private @Id @GeneratedValue Long id;
    private String nom;
    private String type;
    private Plat plat;

    public Restaurant(){}

    public Restaurant(String nom, String type) {

        this.nom = nom;
        this.type = type;
    }

    public Long getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getType() {
        return this.type;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "id=" + this.id + ", nom='" + this.nom + '\'' +
                ", type='" + this.type + '\'' + ", Plat='" + this.plat + '\'' +'}';
    }
}
