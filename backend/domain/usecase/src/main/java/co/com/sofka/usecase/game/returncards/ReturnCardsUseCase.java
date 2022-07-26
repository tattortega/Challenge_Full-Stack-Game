package co.com.sofka.usecase.game.returncards;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.function.BiFunction;


/**
 * Caso de uso para retornar cartas al mazo del juego
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class ReturnCardsUseCase implements BiFunction<Game, String, Mono<Game>> {

    private final GameRepository gameRepository;

    /**
     * Método que obtiene las cartas del jugador y las retorna al juego, si gano o si se retiro
     *
     * @param game     Game
     * @param idPlayer String
     * @return Mono<Game>
     */
    @Override
    public Mono<Game> apply(Game game, String idPlayer) {

        return gameRepository.findById(game.getId())
                .map(game1 -> game1.getPlayers().stream().findFirst().get())
                .map(player -> {
                    game.getCards().addAll(player.getCards());
                    player.setCards(new HashSet<>());
                    return game;
                }).flatMap(gameRepository::save);
    }
}
