package co.com.sofka.usecase.game.startgame;

import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.usecase.card.getallcard.GetAllCardUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class StartGameUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final GetAllCardUseCase getAllCardUseCase;

    @Override
    public Mono<Game> apply(Game game) {

        return gameRepository.findById(game.getId())
                .map(p -> new Game(game.getId(), 1, game.getPlayers(), game.getBoard(), game.getCards()))
                .flatMap(g-> getAllCardUseCase.get().collectList().map(cartas->{
                    g.setCards(cartas);
                    return g;
                }))
                .flatMap(gameRepository::save);
    }
}