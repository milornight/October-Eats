package octo.stage.octobereats.domain.exception;

public class CommandeNePeutPasLivrerException extends Exception{

    public CommandeNePeutPasLivrerException() {
        super("Erreur: La commande n'est pas encore pris par personne pour livrer, il faut un livreur prendre charge de cette commande");
    }
}
