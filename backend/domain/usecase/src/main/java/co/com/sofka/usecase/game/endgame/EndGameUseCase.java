package co.com.sofka.usecase.game.endgame;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.gateways.PlayerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class EndGameUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    @Override
    public Mono<Game> apply(Game game) {

        return gameRepository.findById(game.getId())
                .map(game1 -> {
                    game1.getPlayers().stream().findFirst().get().setScore(100);
                    return game1;
                }).flatMap(gameRepository::save);

    }
}
