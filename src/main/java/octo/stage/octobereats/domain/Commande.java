package octo.stage.octobereats.domain;

import java.util.Map;
import java.util.Objects;

public class Commande {

    private static long count = 0;
    private long idCommande;
    private long idClient;
    private long idRestaurant;
    private long idPlat;
    private int quantite;
    private CommandeStatus commandeStatus;
    //private Map<CommandeStatus, Long> status;


    public Commande(long idClient, long idRestaurant, long idPlat, int quantite) {
        this.idCommande = ++count;
        this.idClient = idClient;
        this.idRestaurant = idRestaurant;
        this.idPlat = idPlat;
        this.quantite = quantite;
        this.commandeStatus = CommandeStatus.ENVOYE;
        commandeStatus.setIdCommande(idCommande);
        //status.put(commandeStatus,idCommande);
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

    public CommandeStatus getCommandeStatus() {
        //status.get(CommandeStatus);
        return commandeStatus;
    }

    public void setCommandeStatus(CommandeStatus commandeStatus) {

        this.commandeStatus.setIdCommande(this.getIdCommande());
        this.commandeStatus = commandeStatus;
    }

    /*public Map<CommandeStatus, Long> getStatus() {
        return status;
    }

    public void setStatus(Map<CommandeStatus, Long> status) {
        this.status = status;
    }*/

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
