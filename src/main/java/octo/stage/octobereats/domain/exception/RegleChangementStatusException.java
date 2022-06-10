package octo.stage.octobereats.domain.exception;

public class RegleChangementStatusException extends Exception{

    public RegleChangementStatusException() {
        super("Erreur : Le changement de la status de la commande n'a pas respecté la logique");
    }
}
