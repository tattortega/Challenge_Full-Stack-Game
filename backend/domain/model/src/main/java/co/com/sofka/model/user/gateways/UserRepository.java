package co.com.sofka.model.user.gateways;

import co.com.sofka.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz del repositorio para el modelo User con sus metodos a implementar
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
public interface UserRepository {
    Mono<User> save(User user);
    Flux<User> findAll();
    Mono<User> findById(String id);
    Mono<Void> deleteById(String id);
}
