package co.com.sofka.usecase.game.startgame;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.card.gateways.CardRepository;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.card.getallcard.GetAllCardUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

class StartGameUseCaseTest {

    @SpyBean
    StartGameUseCase startGameUseCase;
    @MockBean
    GetAllCardUseCase getAllCardUseCase;
    @MockBean
    GameRepository gameRepository;
    @MockBean
    CardRepository cardRepository;


    @BeforeEach
    void setUp() {
        gameRepository = mock(GameRepository.class);
        cardRepository = mock(CardRepository.class);
        getAllCardUseCase = new GetAllCardUseCase(cardRepository);
        startGameUseCase = new StartGameUseCase(gameRepository, getAllCardUseCase);
    }

    @Test
    void startGameTest() {
        Card card1 = new Card("1", "hulk", 150, false, "url", "super fuerza");
        Card card2 = new Card("2", "hombre araña", 160, false, "url", "sentido aracnido");
        Card card3 = new Card("3", "thor", 170, false, "url", "dios del trueno");
        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        Flux<Card> cardFlux = Flux.just(card1, card2, card3);

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

        StepVerifier.create(startGameUseCase.apply(game))
                .expectNextMatches(gameDocument -> {
                    assert gameDocument.getId().equals("1");
                    assert gameDocument.getRound().equals(1);
                    assert gameDocument.getPlayers().containsAll(players);
                    assert gameDocument.getCards().containsAll(cards);
                    assert gameDocument.getBoard().equals(board);
                    return true;
                })
                .verifyComplete();

        verify(gameRepository).findById(game.getId());
    }
}