package co.com.sofka.usecase.game.distributecards;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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
                    .board(game1.getBoard())
                    .cards(removeCard(cardSet, game.getCards()))
                    .build();
            Player player1 = player.toBuilder()
                    .id(player.getId())
                    .score(player.getScore())
                    .user(player.getUser())
                    .cards(cardSet)
                    .turn(player.getTurn())
                    .build();
            playerRepository.save(player1).subscribe();
            return player1;
        }).collect(Collectors.toSet());

        return gameRepository.findById(game.getId())
                .map(p -> p.toBuilder()
                        .id(game.getId())
                        .round(game.getRound())
                        .players(playerSet1)
                        .board(game.getBoard())
                        .cards(game.getCards())
                        .build())
                .flatMap(gameRepository::save)
                .map(game2 -> {
                    Board board = new Board();
                    board.setId(game2.getBoard().getId());
                    board.setCardsBetPlayers(game2.getBoard().getCardsBetPlayers());

                    Map<Integer, Player> turns = new HashMap<>();
                    AtomicInteger index = new AtomicInteger();
                    index.getAndIncrement();

                    game2.getPlayers().forEach(player -> {
                        if(index.intValue() == 1){
                            player.setTurn(Boolean.TRUE);
                        }
                        turns.put(index.getAndIncrement(), player);
                    });
                    board.setTurn(turns);
                    game2.setBoard(board);
                    return game2;
                }).flatMap(gameRepository::save);
    }

    private Set<Card> addCards(Game game) {
        Set<Card> cardSet = new HashSet<>();
        List<Card> cardList = new ArrayList<>(game.getCards());
        Random r = new Random();
        Flux.range(0, 3)
                .map(i -> {
                    int value = r.nextInt(cardList.size()-i);
                    cardSet.add(cardList.get(value));
                    cardList.remove(cardList.get(value));
                    return i;
                }).subscribe();
        return cardSet;
    }

    private List<Card> removeCard(Set<Card> cardsPlayer, List<Card> cardsGame) {
        List<Card> cardListPlayer = new ArrayList<>(cardsPlayer);
        Flux.range(0,cardsPlayer.size())
                .map(i -> {
                    cardsGame.remove(cardListPlayer.get(i));
                    return i;
                }).subscribe();
        return cardsGame;
    }
}

