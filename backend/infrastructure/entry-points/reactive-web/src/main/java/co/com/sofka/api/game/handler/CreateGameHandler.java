package co.com.sofka.api.game.handler;

import co.com.sofka.model.game.Game;
import co.com.sofka.usecase.game.creategame.CreateGameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateGameHandler {

    private final CreateGameUseCase createGameUseCase;

    public Mono<ServerResponse> createGame(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(Game.class)
                .flatMap(createGameUseCase::apply)
                .flatMap(game -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(game));
    }

}
