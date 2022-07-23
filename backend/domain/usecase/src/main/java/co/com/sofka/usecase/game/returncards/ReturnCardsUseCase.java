package co.com.sofka.usecase.game.returncards;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReturnCardsUseCase implements BiFunction<Game, Player, Mono<Game>> {

    private final GameRepository gameRepository;

    @Override
    public Mono<Game> apply(Game game, Player playerx) {

        Flux.fromIterable(game.getPlayers())
                .takeUntil(player -> player.getId().equals(playerx.getId()))
                .map(player -> player.getCards())
                .map(cards -> {
                    List<Card> cards1 = new ArrayList<>(game.getCards());
                    cards1.addAll(cards);
                    game.setCards(cards1);
                    return game;
                });

        Flux.fromIterable(game.getPlayers()).takeUntil(player -> player.getId().equals(playerx.getId()))
                .map(player -> {
                    player.setCards(new HashSet<>());
                    return game;
                }).subscribe();

        return gameRepository.save(game);

    }
}
