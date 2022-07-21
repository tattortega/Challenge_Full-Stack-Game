package co.com.sofka.usecase.game.getgame;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import co.com.sofka.usecase.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetGameUseCase implements Function<String, Mono<Game>> {

    private static final String GAME_CON_EL_ID_NO_EXISTE = "Game con el id %s no existe";

    private final GameRepository gameRepository;

    @Override
    public Mono<Game> apply(String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new NotFoundException(String.format(GAME_CON_EL_ID_NO_EXISTE, id)))
                );
    }
}
