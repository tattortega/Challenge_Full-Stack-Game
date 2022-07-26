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
    public RouterFunction<ServerResponse> routerSDistributeCardsFunction(DistributeCardsInGameHandler distributeCardsInGameHandler) {
        return route(POST("/api/game/distribute-cards"), distributeCardsInGameHandler::distributeCards);
    }

    @Bean
    public RouterFunction<ServerResponse> routerGetGameFunction(GetGameHandler getGameHandler) {
        return route(GET("/api/game/{id}"), getGameHandler::getGame);
    }

    @Bean
    public RouterFunction<ServerResponse> routerCountCardPlayer(CountCardPlayerHandler countCardPlayerHandler) {
        return route(POST("/api/game/count-cards"), countCardPlayerHandler::countCards);
    }

    @Bean
    public RouterFunction<ServerResponse> routerCleanBoard(CleanBoardHandler cleanBoardHandler) {
        return route(POST("/api/game/clean-board"), cleanBoardHandler::cleanBoard);
    }

    @Bean
    public RouterFunction<ServerResponse> routerCountPlayers(CountPlayersHandler countPlayersHandler) {
        return route(POST("/api/game/count-player"), countPlayersHandler::countPlayers);
    }

    @Bean
    public RouterFunction<ServerResponse> routerEndGame(EndGameHandler endGameHandler) {
        return route(POST("/api/game/end-game"), endGameHandler::endGame);
    }

    @Bean
    public RouterFunction<ServerResponse> routerRetirePlayer(RetirePlayerHandler retirePlayerHandler) {
        return route(POST("/api/game/{id}"), retirePlayerHandler::retirePlayer);
    }

    @Bean
    public RouterFunction<ServerResponse> routerBetCards(BetCardsHandler betCardsHandler) {
        return route(POST("/api/game/bet-card/{id}"), betCardsHandler::betCards);
    }

    @Bean
    public RouterFunction<ServerResponse> routerComparateCards(ComparateCardsHandler comparateCardsHandler) {
        return RouterFunctions.route(
                POST("/api/game/comparate-cards").and(contentType(APPLICATION_JSON)), comparateCardsHandler::comparateCards);
    }

}
