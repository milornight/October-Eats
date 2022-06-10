package octo.stage.octobereats.domain.exception;

public class CommandePasPrete extends Exception {

    public CommandePasPrete() {
        super("Erreur : La commande n'est pas encore prête");
    }
}
