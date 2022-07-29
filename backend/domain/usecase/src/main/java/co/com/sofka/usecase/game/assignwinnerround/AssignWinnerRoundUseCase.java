package co.com.sofka.usecase.game.assignwinnerround;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.usecase.game.cleanboard.CleanBoardUseCase;
import co.com.sofka.usecase.game.countcardplayer.CountCardPlayerUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Caso de uso para asignar ganador de la ronda
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class AssignWinnerRoundUseCase implements BiFunction<Game, String, Mono<Game>> {

    private final GameRepository gameRepository;
    private final CountCardPlayerUseCase countCardPlayerUseCase;

    /**
     * Método que recibe el juego y el ID del ganador de la ronda y darle las cartas apostadas
     * Despues de asignar el ganador invoca al caso de uso contar cartas de jugadores
     * @param game Game
     * @param idWinner String
     * @return Mono<Game>
     */
    @Override
    public Mono<Game> apply(Game game, String idWinner) {

        Map<String, Card> mapCards = game.getBoard().getCardsBetPlayers();
        System.out.println("map idplayer y cartas apostadas "+mapCards);
        Set<Card> betCards = new HashSet<>(mapCards.values());
        System.out.println("cartas apostadas");
        Flux.fromIterable(game.getPlayers())
                .filter(player -> player.getId().equals(idWinner))
                .map(player -> {
                    game.getBoard().setWinnerRound(player);
                    return player;
                })
                .map(player -> betCards.stream()
                        .map(card -> {
                            player.getCards().add(card);
                            return player;
                        }).collect(Collectors.toList()))
                .subscribe();
        System.out.println("jugadoresdespuesderone"+ game.getPlayers());

        return countCardPlayerUseCase.apply(game);

    }
}
