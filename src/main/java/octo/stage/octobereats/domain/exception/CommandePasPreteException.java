package octo.stage.octobereats.domain.exception;

public class CommandePasPreteException extends Exception {

    public CommandePasPreteException() {
        super("Erreur : La commande n'est pas encore prête");
    }
}

