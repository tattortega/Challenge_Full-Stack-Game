package co.com.sofka.api.game.handler;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.game.addplayer.AddPlayerUseCase;
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
public class AddPlayerHandler {

    private final AddPlayerUseCase addPlayerUseCase;

    public Mono<ServerResponse> addPlayer(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(Game.class)
                .zipWith(serverRequest.bodyToMono(Player.class))
                .flatMap(game1->  this.addPlayerUseCase.apply(game1.getT1(),game1.getT2()))
                .flatMap(game -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(game));
    }

}
