package octo.stage.octobereats.domain;

import octo.stage.octobereats.domain.exception.LivreurIndisponibleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivreurTest {

    @Test
    public void ToutEstOK_alorsNeThrowPasDErreur(){
        Livreur livreur = new Livreur("M","kiki");

        assertDoesNotThrow(() -> livreur.verifierEstLibre());
    }

    @Test
    public void LivreurNEstPasLibre_alorsThrowLivreurIndisponibleException(){
        Livreur livreur = new Livreur("M","kiki");
        Commande commande = new Commande(1,1,1,1);
        commande.setCommandeStatus(CommandeStatus.EN_LIVRAISON);
        livreur.addCommande(commande);

        assertThrows(LivreurIndisponibleException.class, () -> livreur.verifierEstLibre());
    }

}