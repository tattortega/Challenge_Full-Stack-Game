package co.com.sofka.api.game.handler;

import co.com.sofka.usecase.player.getplayer.GetPlayerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetPlayerHandler {

    private final GetPlayerUseCase getPlayerUseCase;

    public Mono<ServerResponse> getPlayer(ServerRequest serverRequest) {
        return Mono.just(serverRequest
                        .pathVariable("id"))
                .flatMap(this.getPlayerUseCase::apply)
                .flatMap(player -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(player));
    }

}
