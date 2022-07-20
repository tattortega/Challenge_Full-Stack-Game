package co.com.sofka.usecase.game.creategame;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateGameUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;

    @Override
    public Mono<Game> apply(Game game) {
        return gameRepository.save(game);
    }
}
