package co.com.sofka.model.game.gateways;

import co.com.sofka.model.game.Game;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameRepository {

    Mono<Game> save(Game game);
    Flux<Game> findAll();
    Mono<Game> findById(String id);
    Mono<Void> delete(String id);
}
