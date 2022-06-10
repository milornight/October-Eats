package octo.stage.octobereats.domain.exception;

public class LivreurIndisponibleException extends Exception {

    public LivreurIndisponibleException() {
        super("Erreur : Livreur n'a pas encore fini de livrer sa commande précédente");
    }
}
