package co.com.sofka.api.card.handler;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.player.Player;
import co.com.sofka.usecase.card.getallcard.GetAllCardUseCase;
import co.com.sofka.usecase.player.createplayer.CreatePlayerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Handler para obtener todas las cartas
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class GetAllCardHandler {

    private final GetAllCardUseCase getAllCardUseCase;

    public Mono<ServerResponse> getAllCard(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getAllCardUseCase.get(), Card.class));
    }
}
