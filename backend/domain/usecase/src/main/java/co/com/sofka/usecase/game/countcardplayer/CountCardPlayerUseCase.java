package co.com.sofka.usecase.game.countcardplayer;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.game.removeplayer.RemovePlayerUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CountCardPlayerUseCase implements Function<Game, Mono<Game>> {

    //private final GameRepository gameRepository;
    private final RemovePlayerUseCase removePlayerUseCase;

    @Override
    public Mono<Game> apply(Game game) {

        Set<Player> playersWhitoutCards = game.getPlayers()
                .stream()
                .filter(player -> player.getCards().isEmpty())
                .collect(Collectors.toSet());

        return removePlayerUseCase.apply(game, playersWhitoutCards);
    }
}
