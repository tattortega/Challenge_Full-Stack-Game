package co.com.sofka.api.game.handler;

import co.com.sofka.model.game.Game;
import co.com.sofka.usecase.game.comparatecardsingame.ComparateCardsInGameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Handler para obtener comparar las cartas apostadas
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class ComparateCardsHandler {

    private final ComparateCardsInGameUseCase comparateCardsInGameUseCase;

    public Mono<ServerResponse> comparateCards(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(Game.class).log()
                .flatMap(comparateCardsInGameUseCase::apply).log()
                .flatMap(game -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(game));
    }

}
