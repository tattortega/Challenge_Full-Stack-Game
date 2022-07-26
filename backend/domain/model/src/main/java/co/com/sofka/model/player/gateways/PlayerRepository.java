package co.com.sofka.model.player.gateways;

import co.com.sofka.model.player.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz del repositorio para el modelo Player con sus metodos a implementar
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
public interface PlayerRepository {
    
    Mono<Player> save(Player player);
    Flux<Player> findAll();
    Mono<Player> findById(String id);
    Mono<Player> findByUser(String uid);

    Mono<Void> deleteById(String id);
}
