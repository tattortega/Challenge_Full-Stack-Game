package co.com.sofka.usecase.player.getallplayers;

import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllPlayersUseCase implements Supplier<Flux<Player>> {

    private final PlayerRepository playerRepository;


    @Override
    public Flux<Player> get() {
        return playerRepository.findAll();
    }
}
