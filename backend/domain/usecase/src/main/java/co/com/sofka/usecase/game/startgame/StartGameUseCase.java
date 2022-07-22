package co.com.sofka.usecase.game.startgame;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.usecase.game.distributecards.DistributeCardsUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.function.Function;

@RequiredArgsConstructor
public class StartGameUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;

    @Override
    public Mono<Game> apply(Game game) {
        return gameRepository.findById(game.getId())
                .map(p -> new Game(game.getId(), game.getRound(), game.getPlayers(), game.getBoard(), game.getCards()))
                .flatMap(gameRepository::save);
    }
}
