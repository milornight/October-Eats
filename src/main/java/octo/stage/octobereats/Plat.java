package octo.stage.octobereats;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Plat {

    private @Id @GeneratedValue Long id;
    private String nom;
    private float prix;

    public Plat(){}

    public Plat(String nom, float prix) {

        this.nom = nom;
        this.prix = prix;
    }

    public Long getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public float getPrix() {
        return this.prix;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(float Prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Plat{" + "id=" + this.id + ", nom='" + this.nom + '\'' +
                ", prix='" + this.prix + '}';
    }
}
