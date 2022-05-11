package octo.stage.octobereats.domain;

public class PlatCommande {

    private Plat plat;
    private int quantite;

    public PlatCommande(Plat plat, int quantite) {
        this.plat = plat;
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public Plat getPlat() {
        return plat;
    }

    @Override
    public String toString() {
        return "PlatCommande{" + "Plat=" + this.plat + '\'' + ", Quantite='" + this.quantite + '\'' + '}';
    }
}
