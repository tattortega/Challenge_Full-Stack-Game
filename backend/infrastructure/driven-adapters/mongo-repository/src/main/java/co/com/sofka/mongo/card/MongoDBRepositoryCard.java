package co.com.sofka.mongo.card;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBRepositoryCard extends ReactiveMongoRepository<CardDocument, String>, ReactiveQueryByExampleExecutor<CardDocument> {
}
