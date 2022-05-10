package octo.stage.octobereats;

import static java.util.UUID.randomUUID;

public class Restaurant {

    private static long count = 0;
    private long id;
    private String nom;
    private String type;
    private Plat plat;

    public Restaurant(){}

    public Restaurant(String nom, String type) {
        this.id = ++count;
        this.nom = nom;
        this.type = type;
    }

    public long getId() {
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
