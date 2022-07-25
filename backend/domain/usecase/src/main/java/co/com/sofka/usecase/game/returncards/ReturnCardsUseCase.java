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
public class ReturnCardsUseCase implements BiFunction<Game, String, Mono<Game>> {

    private final GameRepository gameRepository;

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
