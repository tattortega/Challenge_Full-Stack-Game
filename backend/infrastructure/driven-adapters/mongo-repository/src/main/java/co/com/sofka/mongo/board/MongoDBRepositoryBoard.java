package co.com.sofka.mongo.board;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

/**
 * Interfaz del repositorio de Mongo Reactive del documento Board
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
public interface MongoDBRepositoryBoard extends ReactiveMongoRepository<BoardDocument, String>, ReactiveQueryByExampleExecutor<BoardDocument> {

}
