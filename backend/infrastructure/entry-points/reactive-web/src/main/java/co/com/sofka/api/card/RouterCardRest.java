package co.com.sofka.api.card;

import co.com.sofka.api.card.handler.GetAllCardHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Router para Card
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Configuration
public class RouterCardRest {


    /**
     * Ruta para obtener todas las cartas
     * @param getAllCardHandler Handler
     * @return RouterFunction<ServerResponse>
     */
    @Bean
    public RouterFunction<ServerResponse> routerGetAllCardFunction(GetAllCardHandler getAllCardHandler) {
        return route(GET("/api/card"), getAllCardHandler::getAllCard);
    }

}
