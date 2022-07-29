package co.com.sofka.usecase.game.getgame;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import co.com.sofka.usecase.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * Caso de uso para obtener un juego
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class GetGameUseCase implements Function<String, Mono<Game>> {
    private static final String GAME_CON_EL_ID_NO_EXISTE = "Game con el id %s no existe";
    private final GameRepository gameRepository;

    @Override
    public Mono<Game> apply(String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new NotFoundException(String.format(GAME_CON_EL_ID_NO_EXISTE, id)))
                );
    }
}
