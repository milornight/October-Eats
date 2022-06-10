package octo.stage.octobereats.domain;

import java.util.List;
import java.util.Objects;

public class Restaurant {

    private static long count = 0;
    private long id;
    private final String nom;
    private final String type;
    private final List<Plat> plats;

    public Restaurant(String nom, String type, List<Plat> plats) {
        this.id = ++count;
        this.nom = nom;
        this.type = type;
        this.plats = plats;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public List<Plat> getPlats() {
        return plats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return id == that.id && Objects.equals(nom, that.nom) && Objects.equals(type, that.type) && Objects.equals(plats, that.plats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, type, plats);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", plats=" + plats +
                '}';
    }
}
