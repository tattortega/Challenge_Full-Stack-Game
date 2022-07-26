package co.com.sofka.model.board.gateways;

import co.com.sofka.model.board.Board;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz del repositorio para el modelo Board con sus metodos a implementar
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
public interface BoardRepository {

    Mono<Board> save(Board board);
    Flux<Board> findAll();
    Mono<Board> findById(String id);
    Mono<Void> deleteById(String id);
}
