package co.com.sofka.usecase.game.removeplayer;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.game.cleanboard.CleanBoardUseCase;
import co.com.sofka.usecase.game.countplayers.CountPlayersUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RemovePlayerUseCase implements BiFunction<Game, Set<Player>, Mono<Game>> {

    private final GameRepository gameRepository;
    private final CleanBoardUseCase cleanBoardUseCase;

    @Override
    public Mono<Game> apply(Game game, Set<Player> playersRemove) {

        playersRemove.stream().map(player -> {
            game.getPlayers().remove(player);
            return game;
        }).collect(Collectors.toSet());

        return cleanBoardUseCase.apply(game)
                .flatMap(gameRepository::save);

    }
}
