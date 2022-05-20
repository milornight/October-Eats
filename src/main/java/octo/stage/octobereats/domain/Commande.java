package octo.stage.octobereats.domain;

import java.util.Objects;


public class Commande {

    private static long count = 0;
    private long idCommande;
    private long idClient;
    private long idRestaurant;
    private long idPlat;
    private int quantite;
    private CommandeStatus commandeStatus;


    public Commande(long idClient, long idRestaurant, long idPlat, int quantite) {
        this.idCommande = ++count;
        this.idClient = idClient;
        this.idRestaurant = idRestaurant;
        this.idPlat = idPlat;
        this.quantite = quantite;
        this.commandeStatus = CommandeStatus.ENVOYE;
    }

    public long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(long idCommande) {
        this.idCommande = idCommande;
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

    public CommandeStatus getCommandeStatus() {
        return commandeStatus;
    }

    public void setCommandeStatus(CommandeStatus commandeStatus) {
        this.commandeStatus = commandeStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commande commande = (Commande) o;
        return idCommande == commande.idCommande && idClient == commande.idClient && idRestaurant == commande.idRestaurant && idPlat == commande.idPlat && quantite == commande.quantite && commandeStatus == commande.commandeStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCommande, idClient, idRestaurant, idPlat, quantite, commandeStatus);
    }

    @Override
    public String toString() {
        return "Commande{" +
                "idCommande=" + idCommande +
                ", idClient=" + idClient +
                ", idRestaurant=" + idRestaurant +
                ", idPlat=" + idPlat +
                ", quantite=" + quantite +
                ", commandeStatus=" + commandeStatus +
                '}';
    }
}
