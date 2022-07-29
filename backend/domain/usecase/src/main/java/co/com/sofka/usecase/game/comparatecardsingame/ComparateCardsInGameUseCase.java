package co.com.sofka.usecase.game.comparatecardsingame;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.game.assignwinnerround.AssignWinnerRoundUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Caso de uso para comparar cartas apostadas en el juego
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class ComparateCardsInGameUseCase implements Function<Game, Mono<Game>> {

    private final AssignWinnerRoundUseCase assignWinnerRoundUseCase;

    /**
     * Método que obtiene la carta con mayor poder para obtener el ID del jugador que aposto esa carta
     * Al finalizar invoca el caso de uso asignar ganador de la ronda
     *
     * @param game Game
     * @return Mono<Game>
     */
    @Override
    public Mono<Game> apply(Game game) {
        Map<String, Card> mapCards = game.getBoard().getCardsBetPlayers();
        String idWinner = mapCards.entrySet()
                .stream()
                .max(Comparator.comparing(a -> a.getValue().getPower()))
                .map(Map.Entry::getKey).get();
        return assignWinnerRoundUseCase.apply(game, idWinner);
    }
}
