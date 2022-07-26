package co.com.sofka.usecase.game.creategame;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

class CreateGameUseCaseTest {

    @SpyBean
    CreateGameUseCase createGameUseCase;
    @MockBean
    GameRepository gameRepository;


    @BeforeEach
    public void setup() {
        gameRepository = mock(GameRepository.class);
        createGameUseCase = new CreateGameUseCase(gameRepository);
    }

    @Test
    void createGameTest() {
        List<Card> cards = new ArrayList<>();
        Set<Player> players = new HashSet<>();
        Board board = new Board();

        Game game = new Game("1", 0, players, board, cards);
        Mono<Game> gameMono = Mono.just(game);

        when(gameRepository.save(Mockito.any(Game.class))).thenReturn(gameMono);

        StepVerifier.create(createGameUseCase.apply(game))
                .expectNextMatches(gameDocument -> {
                    assert gameDocument.getId().equals("1");
                    assert gameDocument.getRound().equals(0);
                    assert gameDocument.getPlayers().equals(players);
                    assert gameDocument.getBoard().equals(board);
                    assert gameDocument.getCards().equals(cards);
                    return true;
                })
                .verifyComplete();

        verify(gameRepository).save(Mockito.any(Game.class));
    }
}