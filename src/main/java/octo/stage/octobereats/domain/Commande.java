package octo.stage.octobereats.domain;

import java.util.Objects;

public class Commande {
    private long idRestaurant;
    private long idPlat;
    private int quantite;

    public Commande(long idRestaurant, long idPlat, int quantite) {
        this.idRestaurant = idRestaurant;
        this.idPlat = idPlat;
        this.quantite = quantite;
    }

    public long getIdRestaurant(){
        return idRestaurant;
    }

    public void setIdRestaurant(long idRestaurant){
        this.idRestaurant = idRestaurant;
    }

    public long getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(long idPlat) {
        this.idPlat = idPlat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commande commande = (Commande) o;
        return idRestaurant == commande.idRestaurant && idPlat == commande.idPlat && quantite == commande.quantite;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRestaurant, idPlat, quantite);
    }

    @Override
    public String toString() {
        return "PlatCommande{" + "idRestaurant=" + this.idRestaurant + '\'' + ", idPlat='" + this.idPlat + '\'' +
                ", quantite='" + this.quantite + '\'' + '}';
    }



}
