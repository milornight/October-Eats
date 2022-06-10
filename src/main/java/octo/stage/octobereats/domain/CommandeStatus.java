package octo.stage.octobereats.domain;

public enum CommandeStatus {
    ENVOYE,
    RECUE,
    EN_PREPARATION,
    PRETE,
    EN_LIVRAISON,
    LIVREE;

    private long idCommande;

    public long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(long idCommande) {
        this.idCommande = idCommande;
    }


    @Override
    public String toString() {
        return "CommandeStatus{" +
                "idCommande=" + idCommande +
                '}';
    }
}