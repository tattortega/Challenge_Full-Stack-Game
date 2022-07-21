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
        System.out.println("entro");
        Set<Player> playerSet1 = new HashSet<>();
        Set<Player> playerSet = game.getPlayers();
        playerSet1 = playerSet.stream().map(player -> {
            Set<Card> cardSet = addCards(game);
            return player.toBuilder()
                    .id(player.getId())
                    .score(player.getScore())
                    .user(player.getUser())
                    .cards(cardSet)
                    .build();
        }).collect(Collectors.toSet());
        Set<Player> finalPlayerSet = playerSet1;
        Flux.fromIterable(finalPlayerSet).map(player -> {
                    Set<Card> cardSet = addCards(game);
                    return player.toBuilder()
                            .id(player.getId())
                            .user(player.getUser())
                            .score(player.getScore())
                            .cards(cardSet)
                            .build();
                })
                .flatMap(playerRepository::save)
                .subscribe();
        return gameRepository.findById(game.getId())
                .map(p -> p.toBuilder()
                        .id(game.getId())
                        .round(game.getRound())
                        .players(finalPlayerSet)
                        .cards(game.getCards())
                        .build())
                .flatMap(gameRepository::save);
    }

    private Set<Card> addCards(Game game) {
        System.out.println("entro2");
        Set<Card> cardSet = new HashSet<>();
        List<Card> cardList = new ArrayList<>(game.getCards());

        Random r = new Random();
        Flux.range(0, 5)
                .map(i -> {
                    r.nextInt(4);
                    cardSet.add(cardList.get(i));
                    return removeCard(cardList.get(i), game);
                }).subscribe();
        return cardSet;
    }

    private Mono<Game> removeCard(Card card, Game game) {
        Set<Card> cardList = new HashSet<>(game.getCards());
        System.out.println("lista original" + cardList);
        cardList.remove(card);
        System.out.println("lista cambiada" + cardList);
//        return Mono.just(game.toBuilder()
//                .id(game.getId())
//                .players(game.getPlayers())
//                .round(game.getRound())
//                .cards(cardList)
//                .build());
//        Game gameMono = new Game(game.getId(), game.getRound(), game.getPlayers(), cardList);
//        return gameRepository.save(gameMono);
        return gameRepository.findById(game.getId())
                .map(p -> p.toBuilder()
                        .id(game.getId())
                        .round(game.getRound())
                        .players(game.getPlayers())
                        .cards(cardList)
                        .build())
                .flatMap(gameRepository::save);
    }
}

