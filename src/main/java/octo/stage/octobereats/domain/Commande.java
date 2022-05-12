package octo.stage.octobereats.domain;

import java.util.List;

public class Commande {
    private long idRestaurant;
    //private List<PlatCommande> platCommande;
    private Plat plat;
    private int quantite;

    public Commande(long idRestaurant, Plat plat, int quantite) {
        this.idRestaurant = idRestaurant;
        this.plat = plat;
        this.quantite = quantite;
    }

    public long getIdRestaurant(){
        return idRestaurant;
    }

    public void setIdRestaurant(long idRestaurant){
        this.idRestaurant = idRestaurant;
    }

    /*public List<PlatCommande> getPlatCommandes() {
        return platCommande;
    }

    public void setPlatCommandes(List<PlatCommande> platCommande) {
        this.platCommande = platCommande;
    }*/

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Commande{" + "idRestaurant=" + this.idRestaurant + '\'' + ", Plat='" + this.plat + '\'' + ", Quantite='" + this.quantite + '\'' + '}';
    }

}
