package co.com.sofka.usecase.game.startgame;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.board.gateways.BoardRepository;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.usecase.board.createboard.CreateBoardUseCase;
import co.com.sofka.usecase.game.distributecards.DistributeCardsUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@RequiredArgsConstructor
public class StartGameUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final BoardRepository boardRepository;

    @Override
    public Mono<Game> apply(Game game) {
        Board board = new Board();
        Mono<Board> monoBoard = boardRepository.save(game.getBoard());

        try {
            board.setId(monoBoard.toFuture().get().getId());
            board.setCardsBetPlayers(monoBoard.toFuture().get().getCardsBetPlayers());
            board.setTurn(monoBoard.toFuture().get().getTurn());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        return gameRepository.findById(game.getId())
                .map(p -> new Game(game.getId(), game.getRound(), game.getPlayers(), board, game.getCards()))
                .flatMap(gameRepository::save);
    }
}
