package co.com.sofka.usecase.game.removeplayer;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.game.cleanboard.CleanBoardUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RemovePlayerUseCaseTest {

    @SpyBean
    RemovePlayerUseCase removePlayerUseCase;
    @MockBean
    CleanBoardUseCase cleanBoardUseCase;
    @MockBean
    GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        gameRepository = mock(GameRepository.class);
        removePlayerUseCase = new RemovePlayerUseCase(gameRepository,cleanBoardUseCase);
    }

    @Test
    void removePlayerTest() {
        Card card1 = new Card("1", "hulk", 150, false, "url", "super fuerza");
        Card card2 = new Card("2", "hombre araña", 160, false, "url", "sentido aracnido");
        Card card3 = new Card("3", "thor", 170, false, "url", "dios del trueno");
        Card card4 = new Card("4", "ironman", 180, false, "url", "cientifico");
        Card card5 = new Card("5", "dr doom", 190, false, "url", "mago");
        Card card6 = new Card("6", "capitan america", 200, false, "url", "soldadoo");
        Card card7 = new Card("7", "ant man", 210, false, "url", "hormiga");
        Card card8 = new Card("8", "morbius", 220, false, "url", "vampiro");
        Card card9 = new Card("9", "mephisto", 230, false, "url", "demonio");
        Card card10 = new Card("10", "dr strange", 240, false, "url", "hechicero");
        Card card11 = new Card("11", "mordo", 240, false, "url", "hechicero");

        Set<Card> cardsPlayer1 = new HashSet<>();
        cardsPlayer1.add(card1);
        cardsPlayer1.add(card2);
        cardsPlayer1.add(card3);

        List<Card> cardsGame = new ArrayList<>();
        cardsGame.add(card10);
        cardsGame.add(card11);

        Set<Player> players = new HashSet<>();
        Player player1 = new Player("1","player1", 0, cardsPlayer1, true, null);
        Player player2 = new Player("2","player2", 0, null, false, null);
        Player player3 = new Player("3","player3", 0, null, false, null);
        players.add(player1);
        players.add(player2);
        players.add(player3);

        Map<Integer, Player> turn = new HashMap<>();
        turn.put(1, player1);
        turn.put(2, player2);
        turn.put(3, player3);

        Map<String, Card> cardsBetPlayers = new HashMap<>();
        cardsBetPlayers.put(player1.getId(), card1);
        cardsBetPlayers.put(player2.getId(), card4);
        cardsBetPlayers.put(player3.getId(), card7);

        Board board = new Board("1", cardsBetPlayers, turn, null);

        Game game = new Game("1", 1, players, board, cardsGame);
        Mono<Game> gameMono = Mono.just(game);

        Set<Player> playersRemove = new HashSet<>();
        playersRemove.add(player2);
        playersRemove.add(player3);

        when(gameRepository.save(Mockito.any(Game.class))).thenReturn(gameMono);
        when(gameRepository.findById(game.getId())).thenReturn(gameMono);

        StepVerifier.create(removePlayerUseCase.apply(game, playersRemove))
                .expectNextMatches(gameDocument -> {
                    assert gameDocument.getId().equals("1");
                    assert gameDocument.getRound().equals(1);
                    assert gameDocument.getPlayers().equals(players);
                    assert gameDocument.getCards().equals(cardsGame);
                    assert gameDocument.getBoard().equals(board);
                    return true;
                })
                .verifyComplete();

        verify(gameRepository).save(Mockito.any(Game.class));
    }
}