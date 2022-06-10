package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.CommandeStatus;
import org.springframework.stereotype.Repository;

import static octo.stage.octobereats.domain.CommandeStatus.*;

@Repository
public class StatusRepo implements StatusRepository{

    public CommandeStatus convertir(String s){
        CommandeStatus status;
        String[] splitted = s.split(" ");
        switch (splitted[1]){
            case "ENVOYE":
                status = ENVOYE;
                break;
            case "RECUE":
                status = RECUE;
                break;
            case "EN_PREPARATION":
                status = EN_PREPARATION;
                break;
            case "PRETE":
                status = PRETE;
                break;
            case "EN_LIVRAISON":
                status = EN_LIVRAISON;
                break;
            case "LIVREE":
                status = LIVREE;
                break;
            default:
                status = null;
                break;
        }
        return status;
    }

}
