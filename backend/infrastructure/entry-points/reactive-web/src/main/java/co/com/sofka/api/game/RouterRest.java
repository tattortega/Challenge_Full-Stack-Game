package co.com.sofka.api.game;

import co.com.sofka.api.game.handler.*;
import co.com.sofka.api.player.handler.CreatePlayerHandler;
import co.com.sofka.model.game.gateways.GameRepository;
import co.com.sofka.usecase.game.creategame.CreateGameUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RouterRest {

    @Bean
    public RouterFunction<ServerResponse> routerCreateGameFunction(CreateGameHandler createGameHandler) {
        return RouterFunctions.route(
                POST("/api/game").and(contentType(APPLICATION_JSON)), createGameHandler::createGame);
//        return route(POST("/api/game").and(contentType(APPLICATION_JSON)), createGameHandler::createGame);
    }

    @Bean
    public RouterFunction<ServerResponse> routerAddPlayersFunction(AddPlayerHandler addPlayerHandler) {
        return route(POST("/api/game/player"), addPlayerHandler::addPlayer);
    }

    @Bean
    public RouterFunction<ServerResponse> routerCreatePlayerFunction(CreatePlayerHandler createPlayerHandler) {
        return route(POST("/api/player"), createPlayerHandler::createPlayer);
    }

    @Bean
    public RouterFunction<ServerResponse> routerGetPlayerFunction(GetPlayerHandler getPlayerHandler) {
        return route(GET("/api/player/{id}"), getPlayerHandler::getPlayer);
    }

    @Bean
    public RouterFunction<ServerResponse> routerGetGameFunction(GetGameHandler getGameHandler) {
        return route(GET("/api/game/{id}"), getGameHandler::getGame);
    }

    @Bean
    public RouterFunction<ServerResponse> routerDistributeCardsFunction(DistributeCardsInGameHandler distributeCardsInGameHandler) {
        return route(POST("/api/game/cards"), distributeCardsInGameHandler::distributeCards);
    }

}
