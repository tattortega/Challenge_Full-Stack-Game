package co.com.sofka.usecase.card.getallcard;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.card.gateways.CardRepository;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.game.cleanboard.CleanBoardUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetAllCardUseCaseTest {



    @SpyBean
    GetAllCardUseCase getAllCardUseCase;
    @MockBean
    CardRepository cardRepository;



    @BeforeEach
    void setUp() {
        cardRepository = mock(CardRepository.class);
        getAllCardUseCase = new GetAllCardUseCase(cardRepository);
    }

    @Test
    void getAllCardsTest() {
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

        Flux<Card> cardsGame = Flux.just(card1, card2, card3, card4, card5, card6, card7, card8, card9, card10);


        when(cardRepository.save(Mockito.any(Card.class))).thenReturn(
                Mono.just(card1), Mono.just(card2), Mono.just(card3), Mono.just(card4), Mono.just(card5),
                Mono.just(card6), Mono.just(card7), Mono.just(card8), Mono.just(card9), Mono.just(card10));
        when(cardRepository.findAll()).thenReturn(cardsGame);


        var list = getAllCardUseCase.get();

        Assertions.assertEquals(list.count().block(),10);

    }
}