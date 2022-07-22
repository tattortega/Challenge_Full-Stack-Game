package co.com.sofka.api.board;

import co.com.sofka.api.board.handler.CreateBoardHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class RouterBoardRest {

    @Bean
    public RouterFunction<ServerResponse> routerCreateBoardFunction(CreateBoardHandler createBoardHandler) {
        return RouterFunctions.route(
                POST("/api/board").and(contentType(APPLICATION_JSON)), createBoardHandler::createBoard);
    }

}
