package co.com.sofka.api.game.handler;

import co.com.sofka.usecase.game.getgame.GetGameUseCase;
import co.com.sofka.usecase.player.getplayer.GetPlayerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

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
