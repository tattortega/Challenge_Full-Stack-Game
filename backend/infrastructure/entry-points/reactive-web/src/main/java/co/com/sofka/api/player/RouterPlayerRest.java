package co.com.sofka.api.player;

import co.com.sofka.api.game.handler.GetPlayerHandler;
import co.com.sofka.api.player.handler.CreatePlayerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class RouterPlayerRest {


    @Bean
    public RouterFunction<ServerResponse> routerCreatePlayerFunction(CreatePlayerHandler createPlayerHandler) {
        return route(POST("/api/player"), createPlayerHandler::createPlayer);
    }

    @Bean
    public RouterFunction<ServerResponse> routerGetPlayerFunction(GetPlayerHandler getPlayerHandler) {
        return route(GET("/api/player/{id}"), getPlayerHandler::getPlayer);
    }

}
