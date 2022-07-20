package co.com.sofka.usecase.player.createplayer;

import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreatePlayerUseCase implements Function<Player, Mono<Player>> {

    private final PlayerRepository playerRepository;
    @Override
    public Mono<Player> apply(Player player) {
        return playerRepository.save(player);
    }
}
