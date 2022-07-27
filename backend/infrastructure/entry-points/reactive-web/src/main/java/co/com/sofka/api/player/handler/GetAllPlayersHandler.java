package co.com.sofka.api.player.handler;

import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.player.getallplayers.GetAllPlayersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetAllPlayersHandler {

    private final GetAllPlayersUseCase getAllPlayersUseCase;

    public Mono<ServerResponse> findAllPlayers(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getAllPlayersUseCase.get(), Player.class));
    }
}