package co.com.sofka.api.game.handler;

import co.com.sofka.model.game.Game;
import co.com.sofka.usecase.game.betcard.BetCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BetCardsHandler {

    private final BetCardUseCase betCardUseCase;

    public Mono<ServerResponse> betCards(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");

        return serverRequest
                .bodyToMono(Game.class)
                .flatMap(game -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(betCardUseCase.apply(game, id), Game.class));
    }

}
