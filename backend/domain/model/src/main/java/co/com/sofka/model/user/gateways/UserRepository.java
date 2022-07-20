package co.com.sofka.model.user.gateways;

import co.com.sofka.model.player.Player;
import co.com.sofka.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> save(User user);
    Flux<User> findAll();
    Mono<User> findById(String id);
    Mono<Void> deleteById(String id);
}
