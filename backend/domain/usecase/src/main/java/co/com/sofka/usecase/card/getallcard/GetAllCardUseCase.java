package co.com.sofka.usecase.card.getallcard;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

/**
 * Caso de uso para obtener todas las cartas del juego
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class GetAllCardUseCase implements Supplier<Flux<Card>> {

    private final CardRepository cardRepository;

    @Override
    public Flux<Card> get() {
        return cardRepository.findAll();
    }
}