package co.com.sofka.api.player.handler;

import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.player.createplayer.CreatePlayerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Handler para crear jugador
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class CreatePlayerHandler {

    private final CreatePlayerUseCase createPlayerUseCase;

    public Mono<ServerResponse> createPlayer(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(Player.class)
                .flatMap(createPlayerUseCase::apply)
                .flatMap(player -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(player));
    }
}
