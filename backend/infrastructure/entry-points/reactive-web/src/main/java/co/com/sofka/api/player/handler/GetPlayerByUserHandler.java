package co.com.sofka.api.player.handler;

import co.com.sofka.usecase.player.getplayer.GetPlayerUseCase;
import co.com.sofka.usecase.player.getplayerbyuser.GetPlayerByUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Handler para obtener jugador
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class GetPlayerByUserHandler {

    private final GetPlayerByUserUseCase getPlayerByUserUseCase;

    public Mono<ServerResponse> getPlayerByUser(ServerRequest serverRequest) {
        return Mono.just(serverRequest
                        .pathVariable("id"))
                .flatMap(this.getPlayerByUserUseCase::apply)
                .flatMap(player -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(player));
    }

}
