package co.com.sofka.mongo.player;

import co.com.sofka.model.player.Player;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Mono;

/**
 * Interfaz del repositorio de Mongo Reactive del documento Player
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
public interface MongoDBRepositoryPlayer extends ReactiveMongoRepository<PlayerDocument, String>, ReactiveQueryByExampleExecutor<PlayerDocument> {

    Mono<Player> findByUser(String uid);
}
