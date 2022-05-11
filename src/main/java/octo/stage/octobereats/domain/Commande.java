package octo.stage.octobereats.domain;

import java.util.List;


public class Commande {
    private long idRestaurant;
    private List<PlatCommande> platCommande;

    public Commande(long idRestaurant, List<PlatCommande> platCommande) {
        this.idRestaurant = idRestaurant;
        this.platCommande = platCommande;
    }

    public long getIdRestaurant(){
        return idRestaurant;
    }

    public List<PlatCommande> getPlatCommandes() {
        return platCommande;
    }


    @Override
    public String toString() {
        return "Commande{" + "idRestaurant=" + this.idRestaurant + '\'' + ", Plats='" + this.platCommande + '\'' + '}';
    }

}
