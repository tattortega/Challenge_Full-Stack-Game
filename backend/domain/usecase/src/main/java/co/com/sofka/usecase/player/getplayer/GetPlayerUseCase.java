package co.com.sofka.usecase.player.getplayer;

import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import co.com.sofka.usecase.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetPlayerUseCase implements Function<String, Mono<Player>> {

    private static final String PLAYER_CON_EL_ID_NO_EXISTE = "Player con el id %s no existe";

    private final PlayerRepository playerRepository;

    @Override
    public Mono<Player> apply(String id) {
        return playerRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new NotFoundException(String.format(PLAYER_CON_EL_ID_NO_EXISTE, id)))
                );
    }
}
