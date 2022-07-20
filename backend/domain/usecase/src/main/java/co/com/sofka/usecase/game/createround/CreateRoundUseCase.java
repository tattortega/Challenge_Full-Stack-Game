package co.com.sofka.usecase.game.createround;

import co.com.sofka.model.game.Game;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateRoundUseCase implements Function<String, Mono<Game>> {


    @Override
    public Mono<Game> apply(String s) {
        return null;
    }
}
