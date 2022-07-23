package co.com.sofka.api.game.handler;

import co.com.sofka.model.game.Game;
import co.com.sofka.usecase.game.countplayers.CountPlayersUseCase;
import co.com.sofka.usecase.game.distributecards.DistributeCardsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CountPlayersHandler {

    private final CountPlayersUseCase countPlayersUseCase;

    public Mono<ServerResponse> countPlayers(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(Game.class)
                .flatMap(countPlayersUseCase::apply)
                .flatMap(game -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(game));
    }

}
