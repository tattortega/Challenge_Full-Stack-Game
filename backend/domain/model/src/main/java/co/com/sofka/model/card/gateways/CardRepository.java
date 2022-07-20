package co.com.sofka.model.card.gateways;

import co.com.sofka.model.card.Card;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CardRepository {

    Mono<Card> save(Card card);
    Flux<Card> findAll();
    Mono<Card> findById(String id);
    Mono<Void> deleteById(String id);


}
