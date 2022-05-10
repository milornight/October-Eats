package octo.stage.octobereats;

import java.util.List;

public class Restaurant {

    private static long count = 0;
    private long id;
    private String nom;
    private String type;
    private List<Plat> plat;

    public Restaurant(String nom, String type, List<Plat> P) {
        this.id = ++count;
        this.nom = nom;
        this.type = type;
        this.plat = P;
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

    public List<Plat> getPlats() {
        return plat;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "id=" + this.id + ", nom='" + this.nom + '\'' +
                ", type='" + this.type + '\'' + ", Plat='" + this.plat + '\'' +'}';
    }
}
