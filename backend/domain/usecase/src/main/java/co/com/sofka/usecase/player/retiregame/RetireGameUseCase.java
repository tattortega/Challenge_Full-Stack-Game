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
import java.util.function.BiFunction;
import java.util.function.Function;

@RequiredArgsConstructor
public class RetireGameUseCase implements BiFunction<String, Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final ReturnCardsUseCase returnCardsUseCase;

    @Override
    public Mono<Game> apply(String idPlayer, Game game) {

        returnCardsUseCase.apply(game, idPlayer);

        Flux.fromIterable(game.getPlayers())
                .takeUntil(player -> player.getId().equals(idPlayer))
                .map(player -> {
                    Set<Player> playerSet = new HashSet<>(game.getPlayers());
                    playerSet.remove(player);
                    game.setPlayers(playerSet);
                    return game;
                }).subscribe();

        return gameRepository.save(game);
    }
}
