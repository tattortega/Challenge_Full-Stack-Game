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

/**
 * Caso de uso para distribuir las cartas a cada jugador
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class DistributeCardsUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;

    /**
     * Metodo que obtiene los jugadores, invoca al metodo agregar cartas a cada jugador y reconstruye el juego
     * Asigna cada jugador con sus cartas en los turnos del tablero
     *
     * @param game Game
     * @return Mono<Game>
     */
    @Override
    public Mono<Game> apply(Game game) {
        Set<Player> playerSet1 = new HashSet<>();
        Set<Player> playerSet = new HashSet<>(game.getPlayers());
        System.out.println("jugadores "+playerSet);
        playerSet.stream().map(player -> {
            System.out.println("jugador "+player);
            Set<Card> cardSet = addCards(game);
            game.setCards(removeCard(cardSet, game.getCards()));
            System.out.println("gamedepuesbuilder "+game);
            player.setCards(cardSet);
            System.out.println("cardsplayer "+player);
            return playerSet1.add(player);
        }).collect(Collectors.toSet());
        playerSet1.stream().sorted();
        System.out.println("playerantesguardar "+ game);
        System.out.println("playerset1 "+playerSet1);
//        Mono<Game> gameSave = gameRepository.save(game);
//        System.out.println("gamsave........." +gameSave);
        return Mono.just(game)
                .map(game2 -> {
                    Board board = new Board();
                    board.setId(game2.getBoard().getId());
                    board.setCardsBetPlayers(game2.getBoard().getCardsBetPlayers());

                    Map<Integer, Player> turns = new HashMap<>();
                    AtomicInteger index = new AtomicInteger();
                    index.getAndIncrement();

                    game2.getPlayers().forEach(player -> {
                        System.out.println("playerdistribuir"+ player);
                        if (index.intValue() == 1) {
                            player.setTurn(Boolean.TRUE);
                            turns.put(index.getAndIncrement(), player);
                        }
                        else {
                            player.setTurn(Boolean.FALSE);
                            turns.put(index.getAndIncrement(), player);
                        }
                    });
                    board.setTurn(turns);
                    game2.setBoard(board);
                    return game2;
                }).flatMap(gameRepository::save);
    }

    /**
     * Método que obtiene el mazo de cartas del juego y aleatoriamente las distribuye a cada jugador
     * Invoca al metodo remover cartas del mazo
     *
     * @param game Game
     * @return Set<Card>
     */
    private Set<Card> addCards(Game game) {
        Set<Card> cardSet = new HashSet<>();
        List<Card> cardList = new ArrayList<>(game.getCards());
        Random r = new Random();
        Flux.range(0, 3)
                .map(i -> {
                    int value = r.nextInt(cardList.size() - i);
                    cardSet.add(cardList.get(value));
                    cardList.remove(cardList.get(value));
                    return i;
                }).subscribe();
        return cardSet;
    }

    /**
     * Método que remueve del mazo las cartas distribuidas a cada jugador
     *
     * @param cardsPlayer Set<Card>
     * @param cardsGame   List<Card>
     * @return List<Card>
     */
    private List<Card> removeCard(Set<Card> cardsPlayer, List<Card> cardsGame) {
        List<Card> cardListPlayer = new ArrayList<>(cardsPlayer);
        Flux.range(0, cardsPlayer.size())
                .map(i -> {
                    cardsGame.remove(cardListPlayer.get(i));
                    return i;
                }).subscribe();
        return cardsGame;
    }
}

