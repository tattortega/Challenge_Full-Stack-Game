package co.com.sofka.usecase.game.removeplayer;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.game.cleanboard.CleanBoardUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Caso de uso para remover jugador(es) del juego
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class RemovePlayerUseCase implements BiFunction<Game, Set<Player>, Mono<Game>> {

    private final GameRepository gameRepository;
    private final CleanBoardUseCase cleanBoardUseCase;

    /**
     * Método que recibe los jugadores que se quedaron sin cartas en el juego
     * Invoca el caso de uso limpiar tablero
     *
     * @param game          Game
     * @param playersRemove Set<Player>
     * @return Mono<Game>
     */
    @Override
    public Mono<Game> apply(Game game, Set<Player> playersRemove) {
        playersRemove.forEach(player -> {
            game.getPlayers().removeIf(player1 ->
                    player1.equals(player));
        });
        return cleanBoardUseCase.apply(game)
                .flatMap(gameRepository::save);

    }
}
