//package co.com.sofka.api.error;
//
//import co.com.sofka.usecase.exception.NotFoundException;
//import org.springframework.boot.autoconfigure.web.WebProperties;
//import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
//import org.springframework.boot.web.reactive.error.ErrorAttributes;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.codec.ServerCodecConfigurer;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.*;
//import reactor.core.publisher.Mono;
//
//import java.util.Optional;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Component
//@Order(-2)
//public class GlobalErrorWebExceptionHandler
//        extends AbstractErrorWebExceptionHandler {
//    private static final String
//            OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR = "Ocurrio un error favor contactar al administrador";
//    private static final ConcurrentHashMap<String, Integer> CODIGOS_ESTADO = new ConcurrentHashMap<>();
//
//    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources,
//                                          ApplicationContext applicationContext, ServerCodecConfigurer configurer) {
//        super(errorAttributes, resources, applicationContext);
//        this.setMessageWriters(configurer.getWriters());
//        CODIGOS_ESTADO.put(NotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
//    }
//
//    @Override
//    protected RouterFunction<ServerResponse> getRoutingFunction(
//            ErrorAttributes errorAttributes) {
//        return RouterFunctions.route(
//                RequestPredicates.all(), this::renderErrorResponse);
//    }
//
//    private Mono<ServerResponse> renderErrorResponse(
//            ServerRequest request) {
//        Throwable error = getError(request);
//        String nameException = error.getClass().getSimpleName();
//        Optional<Integer> codeStatus = Optional.of(CODIGOS_ESTADO.get(nameException));
//        return ServerResponse
//                .status(codeStatus.orElse(HttpStatus.INTERNAL_SERVER_ERROR.value()))
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(codeStatus
//                        .map((code) -> new ErrorResponse(nameException, error.getMessage(), code))
//                        .orElse(
//                                new ErrorResponse(nameException, OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR,
//                                        HttpStatus.INTERNAL_SERVER_ERROR.value())
//                        )
//                );
//    }
//}
