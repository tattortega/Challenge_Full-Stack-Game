package co.com.sofka.api.game.handler;

import co.com.sofka.model.game.Game;
import co.com.sofka.usecase.game.comparatecardsingame.ComparateCardsInGameUseCase;
import co.com.sofka.usecase.game.countcardplayer.CountCardPlayerUseCase;
import co.com.sofka.usecase.game.removeplayer.RemovePlayerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CountCardPlayerHandler {

    private final CountCardPlayerUseCase countCardPlayerUseCase;

    public Mono<ServerResponse> countCards(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(Game.class)
                .flatMap(countCardPlayerUseCase::apply)
                .flatMap(game -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(game));
    }

}
