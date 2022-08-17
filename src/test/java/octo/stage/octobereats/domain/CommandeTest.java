package octo.stage.octobereats.domain;

import octo.stage.octobereats.domain.exception.CommandeNePeutPasLivrerException;
import octo.stage.octobereats.domain.exception.RegleChangementStatusException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandeTest {

    // vérifierPeutChangerDeStatus
    @Test
    public void quandToutEstOk_alorsNeThrowPasDErreur() {
        Commande commande = new Commande(15, 15, 15, 15);
        CommandeStatus nouveauStatus = CommandeStatus.RECUE;

        assertDoesNotThrow(() -> commande.vérifierPeutChangerDeStatus(nouveauStatus));
    }

    @Test
    public void quandLeNouveauStatusNEstPasLeSuivant_alorsThrowRegleChangementStatusException() {
        Commande commande = new Commande(2,2,2,2);
        CommandeStatus nouveauStatus = CommandeStatus.ENVOYE;

        assertThrows(RegleChangementStatusException.class, () -> commande.vérifierPeutChangerDeStatus(nouveauStatus));
    }

    @Test
    public void quandLaCommandeEstPrêteEtPasDeLivreur_alorsThrowCommandeNePeutPasLivrerException(){
        Commande commande = new Commande(2,2,3,4);
        commande.setCommandeStatus(CommandeStatus.PRETE);
        CommandeStatus nouveauStatus = CommandeStatus.EN_LIVRAISON;

        assertThrows(CommandeNePeutPasLivrerException.class, () -> commande.vérifierPeutChangerDeStatus(nouveauStatus));
    }
}