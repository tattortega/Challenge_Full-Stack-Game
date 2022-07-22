package co.com.sofka.usecase.game.distributecards;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DistributeCardsUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;


    @Override
    public Mono<Game> apply(Game game) {
        Set<Player> playerSet1;
        Game game1 = game;
        Set<Player> playerSet = game.getPlayers();
        playerSet1 = playerSet.stream().map(player -> {
            Set<Card> cardSet = addCards(game1);
            game1.toBuilder()
                    .id(game1.getId())
                    .round(game1.getRound())
                    .players(game1.getPlayers())
                    .cards(removeCard(cardSet, game.getCards()))
                    .build();
            Player player1 = player.toBuilder()
                    .id(player.getId())
                    .score(player.getScore())
                    .user(player.getUser())
                    .cards(cardSet)
                    .build();
            playerRepository.save(player1).subscribe();
            return player1;
        }).collect(Collectors.toSet());
        return gameRepository.findById(game.getId())
                .map(p -> p.toBuilder()
                        .id(game.getId())
                        .round(game.getRound())
                        .players(playerSet1)
                        .cards(game.getCards())
                        .build())
                .flatMap(gameRepository::save);
    }

    private Set<Card> addCards(Game game) {
        Set<Card> cardSet = new HashSet<>();
        List<Card> cardList = new ArrayList<>(game.getCards());
        Random r = new Random();
        Flux.range(0, 2)
                .map(i -> {
                    int value = r.nextInt(cardList.size()-i);
                    cardSet.add(cardList.get(value));
                    cardList.remove(cardList.get(value));
                    return i;
                }).subscribe();
        return cardSet;
    }

    private Set<Card> removeCard(Set<Card> cardsPlayer, Set<Card> cardsGame) {
        List<Card> cardListPlayer = new ArrayList<>(cardsPlayer);
        Flux.range(0,cardsPlayer.size())
                .map(i -> {
                    cardsGame.remove(cardListPlayer.get(i));
                    return i;
                }).subscribe();
        return cardsGame;
    }
}

