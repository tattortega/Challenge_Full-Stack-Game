package co.com.sofka.usecase.player.retiregame;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.game.returncards.ReturnCardsUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

/**
 * Caso de uso para cuando un jugador se retira del juego
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class RetireGameUseCase implements BiFunction<String, Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final ReturnCardsUseCase returnCardsUseCase;

    /**
     * Metodo que obtiene el jugador que se retira y obtiene sus cartas para retornarlas al mazo del juego
     *
     * @param idPlayer String
     * @param game Game
     * @return Mono<Game>
     */
    @Override
    public Mono<Game> apply(String idPlayer, Game game) {

        return returnCardsUseCase.apply(game, idPlayer)
                .map(game1 -> {
                    Flux.fromIterable(game1.getPlayers())
                            .filter(player -> player.getId().equals(idPlayer))
                            .map(player -> {
                                Set<Player> playerSet = new HashSet<>(game1.getPlayers());
                                playerSet.remove(player);
                                game1.setPlayers(playerSet);
                                AtomicInteger index = new AtomicInteger();
                                index.getAndIncrement();
                                Map<Integer, Player> playersTurn = new HashMap<>();
                                game1.getBoard().getTurn().forEach((i, player1) -> {
                                    if (!player1.getId().equals(idPlayer)) {
                                        playersTurn.put(index.getAndIncrement(), player1);
                                    }
                                });
                                game1.getBoard().setTurn(playersTurn);
                                return game1;
                            }).subscribe();
                    return game1;
                }).flatMap(gameRepository::save);

    }
}
