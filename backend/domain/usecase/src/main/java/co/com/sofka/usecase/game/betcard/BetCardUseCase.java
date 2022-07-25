package co.com.sofka.usecase.game.betcard;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.game.Game;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.usecase.game.comparatecardsingame.ComparateCardsInGameUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

@RequiredArgsConstructor
public class BetCardUseCase implements BiFunction<Game, String, Mono<Game>> {

    private final GameRepository gameRepository;
    private final ComparateCardsInGameUseCase comparateCardsInGameUseCase;

    @Override
    public Mono<Game> apply(Game game, String idCard) {

        Map<String, Card> betCard = new HashMap<>(game.getBoard().getCardsBetPlayers());

        Flux.fromIterable(game.getBoard().getTurn().keySet())
                .map(k -> {
                    if(game.getBoard().getTurn().get(k).getTurn()){
                        game.getBoard().getTurn().get(k).getCards().forEach(card -> {
                            if(card.getId().equals(idCard)){
                                betCard.put(game.getBoard().getTurn().get(k).getId(), card);
                                game.getBoard().setCardsBetPlayers(betCard);
                                Set<Card> cards = new HashSet<>(game.getBoard().getTurn().get(k).getCards());
                                cards.remove(card);
                                game.getBoard().getTurn().get(k).setCards(cards);
                                game.getPlayers().forEach(player -> {
                                    if(player.getId().equals(game.getBoard().getTurn().get(k).getId())){
                                        player.setCards(cards);
                                    }
                                });
                                game.getBoard().getTurn().get(k).setTurn(Boolean.FALSE);
                                if(k < game.getBoard().getTurn().size()){
                                    game.getBoard().getTurn().get(k+1).setTurn(Boolean.TRUE);
                                }else {
                                    gameRepository.save(game);
                                    Mono<Game> game1 = comparateCardsInGameUseCase.apply(game);
                                    comparateCardsInGameUseCase.apply(game)
                                            .flatMap(gameRepository::save)
                                            .subscribe();
                                }
                            }
                        });

                    }
                    return game;
                }).flatMap(gameRepository::save)
                .subscribe();

        return gameRepository.save(game);
    }
}
