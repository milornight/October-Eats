package octo.stage.octobereats;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public class Plat {

    private UUID id;
    private String nom;
    private float prix;

    public Plat(){}

    public Plat(String nom, float prix) {
        this.id = randomUUID();
        this.nom = nom;
        this.prix = prix;
    }

    public UUID getId() {
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
