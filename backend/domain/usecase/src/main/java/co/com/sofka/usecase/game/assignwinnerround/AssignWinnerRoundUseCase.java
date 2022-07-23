package co.com.sofka.usecase.game.assignwinnerround;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.usecase.game.cleanboard.CleanBoardUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AssignWinnerRoundUseCase implements BiFunction<Game, String, Mono<Game>> {

    private final GameRepository gameRepository;
    private final CleanBoardUseCase cleanBoardUseCase;

    @Override
    public Mono<Game> apply(Game game, String idWinner) {

        Map<String, Card> mapCards = game.getBoard().getCardsBetPlayers();

        Set<Card> betCards = mapCards.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());

        Flux.fromIterable(game.getPlayers())
                .filter(player -> player.getId().equals(idWinner))
                .map(player -> betCards.stream()
                        .map(card -> {
                            player.getCards().add(card);
                            return player;
                        }).collect(Collectors.toList()))
                .subscribe();

        return gameRepository.save(game)
                .flatMap(cleanBoardUseCase::apply);
    }
}
