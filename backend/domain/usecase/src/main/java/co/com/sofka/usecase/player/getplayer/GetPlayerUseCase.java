package co.com.sofka.usecase.player.getplayer;

import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import co.com.sofka.usecase.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * Caso de uso para obtener un jugador por ID
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class GetPlayerUseCase implements Function<String, Mono<Player>> {

    private static final String PLAYER_CON_EL_ID_NO_EXISTE = "Player con el id %s no existe";

    private final PlayerRepository playerRepository;

    @Override
    public Mono<Player> apply(String id) {
        return playerRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new NotFoundException(String.format(PLAYER_CON_EL_ID_NO_EXISTE, id)))
                );
    }
}
