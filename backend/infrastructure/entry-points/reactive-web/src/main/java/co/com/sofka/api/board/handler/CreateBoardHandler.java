package co.com.sofka.api.board.handler;

import co.com.sofka.model.board.Board;
import co.com.sofka.usecase.board.createboard.CreateBoardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateBoardHandler {

    private final CreateBoardUseCase createBoardUseCase;

    public Mono<ServerResponse> createBoard(ServerRequest serverRequest) {
        System.out.println("Entro");
        var idGame = serverRequest.pathVariable("game");
        System.out.println("idGame: " + idGame);
        return serverRequest
                .bodyToMono(Board.class)
                .flatMap(board -> ServerResponse.ok()
                        //.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createBoardUseCase.apply(idGame, board), Board.class));
    }

}
