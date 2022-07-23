package co.com.sofka.usecase.game.cleanboard;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.usecase.game.countcardplayer.CountCardPlayerUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public class CleanBoardUseCase implements Function<Game, Mono<Game>> {

    private final GameRepository gameRepository;
    private final CountCardPlayerUseCase countCardPlayerUseCase;

    @Override
    public Mono<Game> apply(Game game) {

        Board board = new Board();
        board.setId(game.getBoard().getId());
        board.setCardsBetPlayers(new HashMap<>());

        Map<String, Boolean> turns = new HashMap<>();
        game.getPlayers().forEach(player -> turns.put(player.getId(), false));
        board.setTurn(turns);

        return gameRepository.findById(game.getId())
                .map(game1 -> {
                    game.setBoard(board);
                    return game;
                }).flatMap(gameRepository::save)
                .flatMap(countCardPlayerUseCase::apply);
    }
}
