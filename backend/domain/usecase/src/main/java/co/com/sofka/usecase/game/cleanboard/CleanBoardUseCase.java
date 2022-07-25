package co.com.sofka.usecase.game.cleanboard;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.game.countcardplayer.CountCardPlayerUseCase;
import co.com.sofka.usecase.game.endgame.EndGameUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@RequiredArgsConstructor
public class CleanBoardUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final EndGameUseCase endGameUseCase;

    @Override
    public Mono<Game> apply(Game game) {

        if (game.getPlayers().size() == 1){
            gameRepository.save(game);
            return endGameUseCase.apply(game);
        }

        Board board = new Board();
        board.setId(game.getBoard().getId());
        board.setCardsBetPlayers(new HashMap<>());

        Map<Integer, Player> turns = new HashMap<>();
        AtomicInteger index = new AtomicInteger();
        index.getAndIncrement();
        gameRepository.findById(game.getId())
                        .map(game1 -> game.getPlayers())
                .subscribe();

        game.getPlayers().forEach(player -> {
            if(index.intValue() == 1){
                int x = index.intValue();
                game.getBoard().getTurn().get(x).setTurn(Boolean.TRUE);
            }
            turns.put(index.getAndIncrement(), player);
        });
        board.setTurn(turns);
        game.setBoard(board);
        game.setRound(game.getRound()+1);

        return gameRepository.save(game);
    }
}
