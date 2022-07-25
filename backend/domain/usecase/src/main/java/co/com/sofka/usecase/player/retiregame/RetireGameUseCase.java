package co.com.sofka.usecase.player.retiregame;

import co.com.sofka.model.card.Card;
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
import java.util.function.Function;

@RequiredArgsConstructor
public class RetireGameUseCase implements BiFunction<String, Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final ReturnCardsUseCase returnCardsUseCase;

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
                                Map<Integer, Player> playersTurn= new HashMap<>();
                                game1.getBoard().getTurn().forEach((i, player1) -> {
                                    if(!player1.getId().equals(idPlayer)){
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
