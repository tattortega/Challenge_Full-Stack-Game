package co.com.sofka.model.card.gateways;

import co.com.sofka.model.card.Card;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz del repositorio para el modelo Card con sus metodos a implementar
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
public interface CardRepository {

    Mono<Card> save(Card card);
    Flux<Card> findAll();
    Mono<Card> findById(String id);
    Mono<Void> deleteById(String id);


}
