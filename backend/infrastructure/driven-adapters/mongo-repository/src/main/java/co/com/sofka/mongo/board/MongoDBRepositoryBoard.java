package co.com.sofka.mongo.board;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;


public interface MongoDBRepositoryBoard extends ReactiveMongoRepository<BoardDocument, String>, ReactiveQueryByExampleExecutor<BoardDocument> {

}
