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
public class CreateBoardUseCase implements Function<Board, Mono<Board>> {

    private final BoardRepository boardRepository;

    @Override
    public Mono<Board> apply(Board board) {
        return boardRepository.save(board);
    }

}