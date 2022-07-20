package co.com.sofka.usecase.game.addplayer;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import co.com.sofka.usecase.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;
import java.util.function.Function;

@RequiredArgsConstructor
public class AddPlayerUseCase implements BiFunction<Game, Player, Mono<Player>> {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;


    @Override
    public Mono<Player> apply(Game game,Player player) {
        return playerRepository.findById(player.getId())
                .switchIfEmpty(Mono.error(new NotFoundException("No se encontro jugador")))
//                .map(player1 -> player1.toBuilder()
//                        .id(player.getId())
//                        .cards(player.getCards())
//                        .score(player.getScore())
//                        .user(player.getUser())
//                        .build())
                .flatMap(gameRepository::addPlayer);
    }
}
