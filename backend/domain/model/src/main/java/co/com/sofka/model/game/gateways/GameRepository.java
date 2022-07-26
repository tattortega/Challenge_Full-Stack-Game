package co.com.sofka.model.game.gateways;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.player.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz del repositorio para el modelo Game con sus metodos a implementar
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
public interface GameRepository {

    Mono<Game> save(Game game);
//    Mono<Player> addPlayer(Player player);
    Flux<Game> findAll();
    Mono<Game> findById(String id);
    Mono<Void> deleteById(String id);
}
