package co.com.sofka.mongo.card;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.card.gateways.CardRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

/**
 * Adaptador del repositorio de Card para las operaciones en la base de datos
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Repository
public class MongoRepositoryAdapterCard extends AdapterOperations<Card, CardDocument, String, MongoDBRepositoryCard>
 implements CardRepository
{

    public MongoRepositoryAdapterCard(MongoDBRepositoryCard repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Card.class));
    }
}
