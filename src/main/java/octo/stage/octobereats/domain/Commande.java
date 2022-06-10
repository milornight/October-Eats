package octo.stage.octobereats.domain;

import java.util.Objects;

public class Commande {

    private static long count = 0;
    private final long idCommande;
    private final long idClient;
    private final long idRestaurant;
    private final long idPlat;
    private final int quantite;
    private CommandeStatus commandeStatus;
    private long idLivreur;


    public Commande(long idClient, long idRestaurant, long idPlat, int quantite) {
        this.idCommande = ++count;
        this.idClient = idClient;
        this.idRestaurant = idRestaurant;
        this.idPlat = idPlat;
        this.quantite = quantite;
        this.commandeStatus = CommandeStatus.ENVOYE;
        commandeStatus.setIdCommande(idCommande);
        this.idLivreur = 0;
    }

    public long getIdCommande() {
        return idCommande;
    }

    public long getIdClient() {
        return idClient;
    }

    public long getIdRestaurant(){
        return idRestaurant;
    }

    public long getIdPlat() {
        return idPlat;
    }

    public int getQuantite() {
        return quantite;
    }

    public CommandeStatus getCommandeStatus() {
        return commandeStatus;
    }

    public void setCommandeStatus(CommandeStatus commandeStatus) {

        this.commandeStatus.setIdCommande(this.getIdCommande());
        this.commandeStatus = commandeStatus;
    }

    public long getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(long idLivreur) {
        this.idLivreur = idLivreur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commande commande = (Commande) o;
        return idCommande == commande.idCommande && idClient == commande.idClient && idRestaurant == commande.idRestaurant && idPlat == commande.idPlat && quantite == commande.quantite && idLivreur == commande.idLivreur && commandeStatus == commande.commandeStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCommande, idClient, idRestaurant, idPlat, quantite, commandeStatus, idLivreur);
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
                ", idLivreur=" + idLivreur +
                '}';
    }
}
