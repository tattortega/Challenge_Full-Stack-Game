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

@RequiredArgsConstructor
public class DistributeCardsUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;


    @Override
    public Mono<Game> apply(Game game) {
        Set<Card> cardSet = game.getCards();
        return gameRepository.findById(game.getId())
                .map(p -> new Game(game.getId(), game.getRound(), game.getPlayers(), game.getCards()))
                .doOnNext(game1 ->
                        game1.getPlayers().iterator().next().setCards(assignCardsToPlayer(cardSet, game)));
    }

    private Set<Card> assignCardsToPlayer(Set<Card> cards, Game game) {
        Set<Card> cardSet = new HashSet<>();
        Set<Player> playerSet = game.getPlayers();
        Flux.fromIterable(playerSet).map(player-> player.toBuilder()
                .id(player.getId())
                .user(player.getUser())
                .score(player.getScore())
                .cards(addCards(cards, game))
                .build())
                .flatMap(playerRepository::save)
                .subscribe();
        return cardSet;
    }

    private Set<Card> addCards(Set<Card> cards, Game game) {
        Set<Card> cardSet = new HashSet<>();
        List<Card> cardList = new ArrayList<>(cards);

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
        System.out.println("entro3");
        Set<Card> cardList = new HashSet<>(game.getCards());
        System.out.println("lista original"+cardList);
        cardList.remove(card);
        System.out.println("lista cambiada"+cardList);
//        return Mono.just(game.toBuilder()
//                .id(game.getId())
//                .players(game.getPlayers())
//                .round(game.getRound())
//                .cards(cardList)
//                .build());
        Game gameMono = new Game(game.getId(), game.getRound(), game.getPlayers(), cardList);
        System.out.println(gameMono.getPlayers());
        return gameRepository.save(gameMono);
//        return gameRepository.findById(game.getId())
//                .map(p -> )
//                .log()
//                .flatMap(gameRepository::save);
    }
}

