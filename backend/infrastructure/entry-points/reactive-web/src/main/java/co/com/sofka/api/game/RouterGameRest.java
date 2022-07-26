package co.com.sofka.api.game;

import co.com.sofka.api.game.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * Router para Game
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Configuration
public class RouterGameRest {

    /**
     * Ruta para crear nuevo juego
     *
     * @param createGameHandler Handler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> routerCreateGameFunction(CreateGameHandler createGameHandler) {
        return RouterFunctions.route(
                POST("/api/game").and(contentType(APPLICATION_JSON)), createGameHandler::createGame);
    }

    /**
     * Ruta para iniciar juego
     *
     * @param startGameHandler Handler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> routerStartGameFunction(StartGameHandler startGameHandler) {
        return route(POST("/api/game/start"), startGameHandler::startGame);
    }

    /**
     * Ruta para distribuir las cartas
     *
     * @param distributeCardsInGameHandler Handler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> routerSDistributeCardsFunction(DistributeCardsInGameHandler distributeCardsInGameHandler) {
        return route(POST("/api/game/distribute-cards"), distributeCardsInGameHandler::distributeCards);
    }

    /**
     * Ruta para obtener obtener juego
     *
     * @param getGameHandler Handler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> routerGetGameFunction(GetGameHandler getGameHandler) {
        return route(GET("/api/game/{id}"), getGameHandler::getGame);
    }

    /**
     * Ruta para contar cartas del jugador
     *
     * @param countCardPlayerHandler Handler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> routerCountCardPlayer(CountCardPlayerHandler countCardPlayerHandler) {
        return route(POST("/api/game/count-cards"), countCardPlayerHandler::countCards);
    }

    /**
     * Ruta para limpiar tablero
     *
     * @param cleanBoardHandler Handler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> routerCleanBoard(CleanBoardHandler cleanBoardHandler) {
        return route(POST("/api/game/clean-board"), cleanBoardHandler::cleanBoard);
    }

    /**
     * Ruta para finalizar juego
     *
     * @param endGameHandler Handler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> routerEndGame(EndGameHandler endGameHandler) {
        return route(POST("/api/game/end-game"), endGameHandler::endGame);
    }

    /**
     * Ruta para retirar jugador del juego
     *
     * @param retirePlayerHandler Handler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> routerRetirePlayer(RetirePlayerHandler retirePlayerHandler) {
        return route(POST("/api/game/{id}"), retirePlayerHandler::retirePlayer);
    }

    /**
     * Ruta para apostar carta
     *
     * @param betCardsHandler Handler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> routerBetCards(BetCardsHandler betCardsHandler) {
        return route(POST("/api/game/bet-card/{id}"), betCardsHandler::betCards);
    }

    /**
     * Ruta para comparar las cartas apostadas
     *
     * @param comparateCardsHandler Handler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> routerComparateCards(ComparateCardsHandler comparateCardsHandler) {
        return RouterFunctions.route(
                POST("/api/game/comparate-cards").and(contentType(APPLICATION_JSON)), comparateCardsHandler::comparateCards);
    }

}
