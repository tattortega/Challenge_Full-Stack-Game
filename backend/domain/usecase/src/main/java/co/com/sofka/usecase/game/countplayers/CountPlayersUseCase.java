package co.com.sofka.usecase.game.countplayers;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CountPlayersUseCase implements Function<Game, Mono<Boolean>> {

    private final GameRepository gameRepository;
    @Override
    public Mono<Boolean> apply(Game game) {

        return gameRepository.findById(game.getId())
                .map(game1 -> game1.getPlayers().size() == 1);
    }
}
