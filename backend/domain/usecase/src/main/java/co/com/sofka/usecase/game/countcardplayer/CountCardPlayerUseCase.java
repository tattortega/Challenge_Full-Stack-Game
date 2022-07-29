package co.com.sofka.usecase.game.countcardplayer;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.game.removeplayer.RemovePlayerUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Caso de uso para contar las cartas que tiene cada jugador en el jeugo
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class CountCardPlayerUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final RemovePlayerUseCase removePlayerUseCase;

    /**
     * Método que filtra los jugadores que no tienen cartas en el juego
     * Al finalizar invoca el caso de uso remover jugador
     *
     * @param game Game
     * @return Mono<Game>
     */
    @Override
    public Mono<Game> apply(Game game) {
        System.out.println("juegodespuesterminarronda"+game);
        Set<Player> playersWhitoutCards = game.getPlayers()
                .stream()
                .filter(player -> player.getCards().isEmpty())
                .collect(Collectors.toSet());
        System.out.println("jugadoressincartas "+ playersWhitoutCards);
        return removePlayerUseCase.apply(game, playersWhitoutCards);
    }
}
