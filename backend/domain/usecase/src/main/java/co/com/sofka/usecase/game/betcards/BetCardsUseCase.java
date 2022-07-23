package co.com.sofka.usecase.game.betcards;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.usecase.game.comparatecardsingame.ComparateCardsInGameUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public class BetCardsUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final ComparateCardsInGameUseCase comparateCardsInGameUseCase;

    @Override
    public Mono<Game> apply(Game game) {

        Flux.fromIterable(game.getPlayers())
                .delayElements(Duration.ofSeconds(2))
                .map(player -> {
                    Map<String, Card> betCard = new HashMap<>(game.getBoard().getCardsBetPlayers());
                    betCard.put(player.getId(), player.getCards().stream().findFirst().get());
                    game.getBoard().setCardsBetPlayers(betCard);
                    player.getCards().remove(player.getCards().stream().findFirst().get());
                    return player;
                })
                .timeout(Duration.ofSeconds(15))
                .subscribe();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return gameRepository.save(game).flatMap(comparateCardsInGameUseCase::apply);
    }
}
