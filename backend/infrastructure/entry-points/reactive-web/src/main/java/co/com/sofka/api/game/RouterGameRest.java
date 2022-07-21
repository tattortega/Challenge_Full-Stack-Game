package co.com.sofka.api.game;

import co.com.sofka.api.game.handler.*;
import co.com.sofka.api.player.handler.CreatePlayerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RouterGameRest {

    @Bean
    public RouterFunction<ServerResponse> routerCreateGameFunction(CreateGameHandler createGameHandler) {
        return RouterFunctions.route(
                POST("/api/game").and(contentType(APPLICATION_JSON)), createGameHandler::createGame);
    }
    @Bean
    public RouterFunction<ServerResponse> routerStartGameFunction(StartGameHandler startGameHandler) {
        return route(POST("/api/game/start"), startGameHandler::startGame);
    }

    @Bean
    public RouterFunction<ServerResponse> routerGetGameFunction(GetGameHandler getGameHandler) {
        return route(GET("/api/game/{id}"), getGameHandler::getGame);
    }


}
