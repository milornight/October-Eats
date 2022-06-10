package octo.stage.octobereats.infra.repository;

import octo.stage.octobereats.domain.CommandeStatus;

public interface StatusRepository {

    public CommandeStatus convertir(String s);
}
