package octo.stage.octobereats.domain;

import java.util.Objects;

public class Livreur {

    private static long count = 0;
    private long id;
    private String nom;
    private String prenom;

    public Livreur(String nom, String prenom) {
        this.id = ++count;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livreur livreur = (Livreur) o;
        return id == livreur.id && Objects.equals(nom, livreur.nom) && Objects.equals(prenom, livreur.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom);
    }

    @Override
    public String toString() {
        return "Livreur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
