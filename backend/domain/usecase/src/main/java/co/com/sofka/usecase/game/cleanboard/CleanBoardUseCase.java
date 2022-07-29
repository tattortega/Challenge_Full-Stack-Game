package co.com.sofka.usecase.game.cleanboard;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.game.countcardplayer.CountCardPlayerUseCase;
import co.com.sofka.usecase.game.endgame.EndGameUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * Caso de uso para limpiar el tablero
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class CleanBoardUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final EndGameUseCase endGameUseCase;

    /**
     * Método que limpia las cartas apostadas en la ronda anterior
     * Asigna al primer jugador de la lista el turno en true para que apueste en la nueva ronda
     * Si el tamaño de la lista de jugadores es 1 invoca al caso de uso finalizar juego
     *
     * @param game Game
     * @return Mono<Game>
     */
    @Override
    public Mono<Game> apply(Game game) {
        Board board = new Board();
        board.setId(game.getBoard().getId());
        board.setCardsBetPlayers(new HashMap<>());
        board.setWinnerRound(game.getBoard().getWinnerRound());

        Map<Integer, Player> turns = new HashMap<>();
        AtomicInteger index = new AtomicInteger();
        index.getAndIncrement();

        game.getPlayers().forEach(player -> {
            turns.put(index.getAndIncrement(), player);
        });
        board.setTurn(turns);
        game.setBoard(board);
        game.setRound(game.getRound() + 1);

        if (game.getPlayers().size() == 1) {
            gameRepository.save(game);
            return endGameUseCase.apply(game);
        }
        return gameRepository.save(game);
    }
}
