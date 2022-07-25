package co.com.sofka.usecase.game.comparatecardsingame;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.game.assignwinnerround.AssignWinnerRoundUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ComparateCardsInGameUseCase implements Function<Game, Mono<Game>> {

    private final AssignWinnerRoundUseCase assignWinnerRoundUseCase;

    @Override
    public Mono<Game> apply(Game game) {

        Map<String, Card> mapCards = game.getBoard().getCardsBetPlayers();

        String idWinner = mapCards.entrySet()
                .stream()
                .max((a, b) -> a.getValue().getPower().compareTo(b.getValue().getPower()))
                .map(Map.Entry::getKey).get();

        return assignWinnerRoundUseCase.apply(game, idWinner);
    }
}
