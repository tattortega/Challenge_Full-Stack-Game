package co.com.sofka.model.board.gateways;

import co.com.sofka.model.board.Board;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BoardRepository {

    Mono<Board> save(Board board);
    Flux<Board> findAll();
    Mono<Board> findById(String id);
    Mono<Void> deleteById(String id);
}
