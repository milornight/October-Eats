package octo.stage.octobereats.domain;

import java.util.Objects;

public class Commande {
    private long idClient;
    private long idRestaurant;
    private long idPlat;
    private int quantite;

    public Commande( long idClient, long idRestaurant, long idPlat, int quantite) {
        this.idClient = idClient;
        this.idRestaurant = idRestaurant;
        this.idPlat = idPlat;
        this.quantite = quantite;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
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
        return idClient == commande.idClient && idRestaurant == commande.idRestaurant && idPlat == commande.idPlat && quantite == commande.quantite;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, idRestaurant, idPlat, quantite);
    }

    @Override
    public String toString() {
        return "Commande{" +
                "idClient=" + idClient +
                ", idRestaurant=" + idRestaurant +
                ", idPlat=" + idPlat +
                ", quantite=" + quantite +
                '}';
    }
}
