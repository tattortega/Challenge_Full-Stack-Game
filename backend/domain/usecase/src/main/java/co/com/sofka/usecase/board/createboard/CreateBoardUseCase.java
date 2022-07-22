package co.com.sofka.usecase.board.createboard;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.board.gateways.BoardRepository;
import co.com.sofka.model.game.gateways.GameRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

@RequiredArgsConstructor
public class CreateBoardUseCase implements BiFunction<String, Board, Mono<Board>> {

    private final BoardRepository boardRepository;
    private final GameRepository gameRepository;

    @Override
    public Mono<Board> apply(String idGame, Board board) {
        System.out.println("Entrando al caso de uso");
        Mono<Board> boardMono = boardRepository.save(board);
        Board board1 = new Board();
        boardMono.flatMap(board2 -> {
            board1.setId(board2.getId());
            board1.setCardsBetPlayers(board2.getCardsBetPlayers());
            board1.setTurn(board2.getTurn());
            return Mono.just(board2);
        });
        gameRepository.findById(idGame)
                .map(game -> game.toBuilder()
                        .id(game.getId())
                        .round(game.getRound())
                        .players(game.getPlayers())
                        .board(board1)
                        .cards(game.getCards())
                        .build())
                .flatMap(gameRepository::save);
        return boardMono;
    }
}
