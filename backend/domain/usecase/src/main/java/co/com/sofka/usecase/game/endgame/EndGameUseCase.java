package co.com.sofka.usecase.game.endgame;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.gateways.PlayerRepository;
import co.com.sofka.usecase.game.returncards.ReturnCardsUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * Caso de uso para finalizar juego
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class EndGameUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final ReturnCardsUseCase returnCardsUseCase;

    /**
     * Método que busca el unico jugador que quedo en el juego y le asigna un puntaje
     * Invoca el caso de uso para retornar las cartas al mazo del juego
     *
     * @param game Game
     * @return Mono<Game>
     */
    @Override
    public Mono<Game> apply(Game game) {
        System.out.println("juegodespuesganadorjuego"+ game);
        gameRepository.findById(game.getId())
                .map(game1 -> {
                    game1.getPlayers().stream().findFirst().get().setScore(100);
                    return game1;
                }).flatMap(game1 -> returnCardsUseCase.apply(game1, game1.getPlayers().stream().findFirst().get().getId()))
                .flatMap(gameRepository::save).subscribe();

        return gameRepository.save(game);

    }
}
