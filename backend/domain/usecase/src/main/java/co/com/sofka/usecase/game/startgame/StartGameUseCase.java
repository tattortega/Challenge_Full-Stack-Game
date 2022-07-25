package co.com.sofka.usecase.game.startgame;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.board.gateways.BoardRepository;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.card.gateways.CardRepository;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.board.createboard.CreateBoardUseCase;
import co.com.sofka.usecase.card.getallcard.GetAllCardUseCase;
import co.com.sofka.usecase.game.distributecards.DistributeCardsUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

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