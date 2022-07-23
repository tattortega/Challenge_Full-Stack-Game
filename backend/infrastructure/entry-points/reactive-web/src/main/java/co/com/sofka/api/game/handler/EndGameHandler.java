package co.com.sofka.api.game.handler;

import co.com.sofka.model.game.Game;
import co.com.sofka.usecase.game.countplayers.CountPlayersUseCase;
import co.com.sofka.usecase.game.endgame.EndGameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EndGameHandler {

    private final EndGameUseCase endGameUseCase;

    public Mono<ServerResponse> endGame(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(Game.class)
                .flatMap(endGameUseCase::apply)
                .flatMap(game -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(game));
    }

}
