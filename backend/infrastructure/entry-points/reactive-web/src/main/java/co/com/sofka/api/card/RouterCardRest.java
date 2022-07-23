package co.com.sofka.api.card;

import co.com.sofka.api.card.handler.GetAllCardHandler;
import co.com.sofka.api.game.handler.GetPlayerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class RouterCardRest {


    @Bean
    public RouterFunction<ServerResponse> routerGetAllCardFunction(GetAllCardHandler getAllCardHandler) {
        return route(GET("/api/card"), getAllCardHandler::getAllCard);
    }

}
