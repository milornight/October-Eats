package octo.stage.octobereats.domain.exception;

public class CommandeDejaPrisException extends Exception{

    public CommandeDejaPrisException() {
        super("Erreur : La commande est déjà pris par l'autre livreur");
    }
}
