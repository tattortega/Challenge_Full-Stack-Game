package co.com.sofka.api.player;

import co.com.sofka.api.player.handler.GetAllPlayersHandler;
import co.com.sofka.api.player.handler.GetPlayerHandler;
import co.com.sofka.api.player.handler.CreatePlayerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Router para Player
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Configuration
public class RouterPlayerRest {

    /**
     * Ruta para crear jugador
     *
     * @param createPlayerHandler Handler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> routerCreatePlayerFunction(CreatePlayerHandler createPlayerHandler) {
        return route(POST("/api/player"), createPlayerHandler::createPlayer);
    }

    /**
     * Ruta para obtener jugador
     *
     * @param getPlayerHandler Handler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> routerGetPlayerFunction(GetPlayerHandler getPlayerHandler) {
        return route(GET("/api/player/{id}"), getPlayerHandler::getPlayer);
    }

    /**
     * Ruta para obtener todos los jugadores
     *
     * @param getAllPlayersHandler Handler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> routerFindAllPlayersFunction(GetAllPlayersHandler getAllPlayersHandler) {
        return route(GET("/api/players"), getAllPlayersHandler::findAllPlayers);
    }

}
