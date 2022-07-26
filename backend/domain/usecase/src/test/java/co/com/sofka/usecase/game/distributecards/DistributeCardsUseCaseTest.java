package co.com.sofka.usecase.game.distributecards;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.card.gateways.CardRepository;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import co.com.sofka.usecase.card.getallcard.GetAllCardUseCase;
import co.com.sofka.usecase.game.startgame.StartGameUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DistributeCardsUseCaseTest {

    @SpyBean
    DistributeCardsUseCase distributeCardsUseCase;
    @MockBean
    GetAllCardUseCase getAllCardUseCase;
    @MockBean
    GameRepository gameRepository;
    @MockBean
    CardRepository cardRepository;
    @MockBean
    PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        gameRepository = mock(GameRepository.class);
        cardRepository = mock(CardRepository.class);
        getAllCardUseCase = new GetAllCardUseCase(cardRepository);
        distributeCardsUseCase = new DistributeCardsUseCase(gameRepository);
    }

    @Test
    void distributeCardsTest() {
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


        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        cards.add(card8);
        cards.add(card9);
        cards.add(card10);
        cards.add(card11);
        Flux<Card> cardFlux = Flux.just(card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11);

        Set<Player> players = new HashSet<>();
        Player player1 = new Player("1", 0, null, false, null);
        Player player2 = new Player("2", 0, null, false, null);
        Player player3 = new Player("3", 0, null, false, null);
        players.add(player1);
        players.add(player2);
        players.add(player3);

        Board board = new Board();

        Game game = new Game("1", 1, players, board, cards);
        Mono<Game> gameMono = Mono.just(game);

        when(gameRepository.save(Mockito.any(Game.class))).thenReturn(gameMono);
        when(gameRepository.findById(game.getId())).thenReturn(gameMono);
        when(cardRepository.findAll()).thenReturn(cardFlux);

        StepVerifier.create(distributeCardsUseCase.apply(game))
                .expectNextMatches(gameDocument -> {
                    assert gameDocument.getId().equals("1");
                    assert gameDocument.getRound().equals(1);
                    assert gameDocument.getPlayers().equals(players);
                    assert gameDocument.getCards().equals(cards);
                    assert gameDocument.getBoard().equals(game.getBoard());
                    return true;
                })
                .verifyComplete();

        verify(gameRepository).findById(game.getId());
    }
}