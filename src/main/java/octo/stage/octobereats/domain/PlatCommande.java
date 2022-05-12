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

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    @Override
    public String toString() {
        return "PlatCommande{" + "Plat=" + this.plat + '\'' + ", Quantite='" + this.quantite + '\'' + '}';
    }
}
