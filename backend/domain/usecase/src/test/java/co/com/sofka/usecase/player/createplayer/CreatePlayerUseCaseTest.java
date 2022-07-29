package co.com.sofka.usecase.player.createplayer;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

class CreatePlayerUseCaseTest {

    @SpyBean
    CreatePlayerUseCase createPlayerUseCase;
    @MockBean
    PlayerRepository playerRepository;


    @BeforeEach
    public void setup(){
        playerRepository = mock(PlayerRepository.class);
        createPlayerUseCase = new CreatePlayerUseCase(playerRepository);
    }

    @Test
    void createPlayerTest() {
        Set<Card> cards = new HashSet<>();
        String user = "12345";
        Player player = new Player("1","player1",0,cards,false,user);
        Mono<Player> playerMono = Mono.just(player);

        when(playerRepository.save(Mockito.any(Player.class))).thenReturn(playerMono);

        StepVerifier.create(createPlayerUseCase.apply(player))
                .expectNextMatches(playerDocument -> {
                    assert playerDocument.getId().equals("1");
                    assert playerDocument.getScore().equals(0);
                    assert playerDocument.getCards().equals(cards);
                    assert playerDocument.getTurn().equals(false);
                    assert playerDocument.getUser().equals(user);
                    return true;
                })
                .verifyComplete();

        verify(playerRepository).save(Mockito.any(Player.class));
    }
}