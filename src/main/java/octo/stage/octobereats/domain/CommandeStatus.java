package octo.stage.octobereats.domain;

public enum CommandeStatus {
    ENVOYE,
    RECUE,
    EN_PREPARATION,
    EN_LIVRAISON,
    LiVREE;

    /*public static int apply(CommandeStatus commandeStatus) {
        switch(commandeStatus) {
            case ENVOYE:
                return 0;
            case RECUE:
                return 1;
            case EN_PREPARATION:
                return 2;
            case EN_LIVRAISON:
                return 3;
            case LiVREE:
                return 4;
            default:
                throw new UnsupportedOperationException();
        }
    }*/
}
