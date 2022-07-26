package co.com.sofka.usecase.player.createplayer;

import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * Caso de uso para crear un nuevo jugador
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class CreatePlayerUseCase implements Function<Player, Mono<Player>> {

    private final PlayerRepository playerRepository;

    @Override
    public Mono<Player> apply(Player player) {
        return playerRepository.save(player);
    }
}
