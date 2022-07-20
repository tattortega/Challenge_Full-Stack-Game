package co.com.sofka.model.game.gateways;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.player.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameRepository {

    Mono<Game> save(Game game);
//    Mono<Player> addPlayer(Player player);
    Flux<Game> findAll();
    Mono<Game> findById(String id);
    Mono<Void> deleteById(String id);
}
