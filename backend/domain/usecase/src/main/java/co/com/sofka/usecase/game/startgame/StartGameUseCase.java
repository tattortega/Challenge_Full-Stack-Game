package co.com.sofka.usecase.game.startgame;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.usecase.card.getallcard.GetAllCardUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * Caso de uso para iniciar juego
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class StartGameUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final GetAllCardUseCase getAllCardUseCase;

    /**
     * Método que obtiene el juego creado e invoca obtener cartas para agregarlas al mazo del juego
     *
     * @param game Game
     * @return Mono<Game>
     */
    @Override
    public Mono<Game> apply(Game game) {
        return gameRepository.findById(game.getId())
                .map(p -> new Game(game.getId(), 1, game.getPlayers(), game.getBoard(), game.getCards()))
                .flatMap(g -> getAllCardUseCase.get().collectList().map(cartas -> {
                    g.setCards(cartas);
                    return g;
                }))
                .flatMap(gameRepository::save);
    }
}