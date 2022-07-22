package co.com.sofka.usecase.card.getallcard;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllCardUseCase implements Supplier<Flux<Card>> {

    private final CardRepository cardRepository;

    @Override
    public Flux<Card> get() {
        return cardRepository.findAll();
    }
}