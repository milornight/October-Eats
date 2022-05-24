package octo.stage.octobereats.infra.flux;

import octo.stage.octobereats.domain.Commande;
import org.springframework.stereotype.Component;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Component
public class CommandeFlux {

    private ConnectableFlux<Commande> commandesPublisher;
    private FluxSink<Commande> commandesStream;

    public CommandeFlux() {
        Flux<Commande> publisher = Flux.create(emitter -> {
            commandesStream = emitter;
        });

        commandesPublisher = publisher.publish();
        commandesPublisher.connect();
    }

    public ConnectableFlux<Commande> getCommandesPublisher() {
        return commandesPublisher;
    }

    public FluxSink<Commande> getCommandesStream() {
        return commandesStream;
    }

}
