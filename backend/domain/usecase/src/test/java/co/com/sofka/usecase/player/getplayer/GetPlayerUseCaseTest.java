package co.com.sofka.usecase.player.getplayer;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import co.com.sofka.model.user.User;
import co.com.sofka.usecase.player.createplayer.CreatePlayerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetPlayerUseCaseTest {

    @SpyBean
    GetPlayerUseCase getPlayerUseCase;
    @MockBean
    PlayerRepository playerRepository;


    @BeforeEach
    public void setup(){
        playerRepository = mock(PlayerRepository.class);
        getPlayerUseCase = new GetPlayerUseCase(playerRepository);
    }

    @Test
    void getPlayerTest() {
        Set<Card> cards = new HashSet<>();
        User user = new User();

        Player player = new Player("1",0,cards,false,user);
        Mono<Player> playerMono = Mono.just(player);

        when(playerRepository.save(Mockito.any(Player.class))).thenReturn(playerMono);
        when(playerRepository.findById(player.getId())).thenReturn(playerMono);

        StepVerifier.create(getPlayerUseCase.apply(player.getId()))
                .expectNextMatches(playerDocument -> {
                    assert playerDocument.getId().equals("1");
                    assert playerDocument.getScore().equals(0);
                    assert playerDocument.getCards().equals(cards);
                    assert playerDocument.getTurn().equals(false);
                    assert playerDocument.getUser().equals(user);
                    return true;
                })
                .verifyComplete();

        verify(playerRepository).findById(player.getId());
    }
}