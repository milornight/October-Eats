package octo.stage.octobereats.domain;

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
    public String toString() {
        return "Commande{" + "idRestaurant=" + this.idRestaurant + '\'' + ", idPlat='" + this.idPlat + '\'' +
                ", Quantite='" + this.quantite + '\'' + '}';
    }



}
