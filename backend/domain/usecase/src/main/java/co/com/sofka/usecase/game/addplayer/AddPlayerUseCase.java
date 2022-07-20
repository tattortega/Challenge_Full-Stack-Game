package co.com.sofka.usecase.game.addplayer;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import co.com.sofka.usecase.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public class AddPlayerUseCase implements BiFunction<Game, Player, Mono<Object>> {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;


    @Override
    public Mono<Object> apply(Game game, Player player) {
        Set<Player> playerSet = game.getPlayers();
//        return playerRepository.findById(player.getId())
//                .switchIfEmpty(Mono.error(new NotFoundException("No se encontro jugador")))
////                .map(player1 -> player1.toBuilder()
////                        .id(player.getId())
////                        .cards(player.getCards())
////                        .score(player.getScore())
////                        .user(player.getUser())
////                        .build())
//                .map(player1 -> {
//                    playerSet.add(player1);
//                    return game.setPlayers(playerSet);
//                });
        Mono<Player> playername = playerRepository.findById(player.getId());
        playerSet.add(playername.block());
        return gameRepository.findById(game.getId())
                .switchIfEmpty(Mono.error(new NotFoundException("No se encontro juego")))
                .map(game1 -> game1.toBuilder()
                        .id(game.getId())
                        .players(playerSet)
                        .board(game.getBoard())
                        .round(game.getRound())
                        .cards(game.getCards())
                        .build())
                .flatMap(gameRepository::save);
    }
}
