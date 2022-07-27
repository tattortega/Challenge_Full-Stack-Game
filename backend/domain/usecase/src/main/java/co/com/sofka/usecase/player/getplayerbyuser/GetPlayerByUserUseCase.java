package co.com.sofka.usecase.player.getplayerbyuser;

import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import co.com.sofka.usecase.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetPlayerByUserUseCase implements Function<String, Mono<Player>> {

    private static final String PLAYER_CON_EL_USER_NO_EXISTE = "Player con el user %s no existe";

    private final PlayerRepository playerRepository;

    @Override
    public Mono<Player> apply(String uid) {
        return playerRepository.findByUser(uid)
                .switchIfEmpty(
                        Mono.error(new NotFoundException(String.format(PLAYER_CON_EL_USER_NO_EXISTE, uid)))
                );
    }
}
