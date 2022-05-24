package octo.stage.octobereats.infra.flux;

import octo.stage.octobereats.domain.CommandeStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Component
public class StatusFlux {

    private ConnectableFlux<CommandeStatus> statusPublisher;
    private FluxSink<CommandeStatus> statusStream;

    public StatusFlux() {
        Flux<CommandeStatus> publisher = Flux.create(emitter -> {
            statusStream = emitter;
        });

        statusPublisher = publisher.publish();
        statusPublisher.connect();
    }

    public ConnectableFlux<CommandeStatus> getStatusPublisher() {
        return statusPublisher;
    }

    public FluxSink<CommandeStatus> getStatusStream() {
        return statusStream;
    }

}
