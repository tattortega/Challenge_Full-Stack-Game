package co.com.sofka.usecase.game.startgame;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.board.gateways.BoardRepository;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.card.gateways.CardRepository;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.usecase.board.createboard.CreateBoardUseCase;
import co.com.sofka.usecase.card.getallcard.GetAllCardUseCase;
import co.com.sofka.usecase.game.distributecards.DistributeCardsUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class StartGameUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final GetAllCardUseCase getAllCardUseCase;

    @Override
    public Mono<Game> apply(Game game) {
        Board board = new Board();
        board.setId(game.getBoard().getId());
        board.setCardsBetPlayers(game.getBoard().getCardsBetPlayers());

        Map<String, Boolean> turns = new HashMap<>();
        game.getPlayers().forEach(player -> turns.put(player.getId(), false));
        board.setTurn(turns);

        return gameRepository.findById(game.getId())
                .map(p -> new Game(game.getId(), game.getRound(), game.getPlayers(), game.getBoard(), game.getCards()))
                .flatMap(g-> getAllCardUseCase.get().collectList().map(cartas->{
                    g.setCards(cartas);
                    return g;
                }))
                .map(p -> {
                    p.setBoard(board);
                    return p;
                })
                .flatMap(gameRepository::save);
    }
}