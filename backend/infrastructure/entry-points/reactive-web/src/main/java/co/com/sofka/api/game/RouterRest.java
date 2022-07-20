package co.com.sofka.api.game;

import co.com.sofka.api.game.handler.AddPlayerHandler;
import co.com.sofka.api.game.handler.CreateGameHandler;
import co.com.sofka.api.player.handler.CreatePlayerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RouterRest {

    @Bean
    public RouterFunction<ServerResponse> routerCreateGameFunction(CreateGameHandler createGameHandler) {
        return route(POST("/api/game"), createGameHandler::createGame);
    }

    @Bean
    public RouterFunction<ServerResponse> routerAddPlayersFunction(AddPlayerHandler addPlayerHandler) {
        return route(POST("/api/game/player"), addPlayerHandler::addPlayer);
    }

    @Bean
    public RouterFunction<ServerResponse> routerCreatePlayerFunction(CreatePlayerHandler createPlayerHandler) {
        return route(POST("/api/player"), createPlayerHandler::createPlayer);
    }
}
