package co.com.sofka.api.game.handler;

import co.com.sofka.usecase.game.getgame.GetGameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Handler para obtener juego
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class GetGameHandler {

    private final GetGameUseCase getGameUseCase;

    public Mono<ServerResponse> getGame(ServerRequest serverRequest) {
        return Mono.just(serverRequest
                        .pathVariable("id"))
                .flatMap(this.getGameUseCase::apply)
                .flatMap(game -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(game));
    }

}
