package co.com.sofka.api.game.handler;

import co.com.sofka.model.game.Game;
import co.com.sofka.usecase.board.assignturnofplayer.AssignTurnOfPlayerUseCase;
import co.com.sofka.usecase.board.createboard.CreateBoardUseCase;
import co.com.sofka.usecase.game.creategame.CreateGameUseCase;
import co.com.sofka.usecase.game.distributecards.DistributeCardsUseCase;
import co.com.sofka.usecase.game.startgame.StartGameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StartGameHandler {

    private final StartGameUseCase startGameUseCase;
    private final DistributeCardsUseCase distributeCardsUseCase;
    private final AssignTurnOfPlayerUseCase assignTurnOfPlayerUseCase;

    public Mono<ServerResponse> startGame(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(Game.class)
                .flatMap(startGameUseCase::apply)
                .flatMap(distributeCardsUseCase::apply)
                //.flatMap(assignTurnOfPlayerUseCase::apply)
                .flatMap(game -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(game));
    }

}
