package co.com.sofka.usecase.game.creategame;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * Caso de uso para crear un nuevo juego
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class CreateGameUseCase implements Function<Game, Mono<Game>> {
    private final GameRepository gameRepository;

    @Override
    public Mono<Game> apply(Game game) {
        return gameRepository.save(game);
    }
}
