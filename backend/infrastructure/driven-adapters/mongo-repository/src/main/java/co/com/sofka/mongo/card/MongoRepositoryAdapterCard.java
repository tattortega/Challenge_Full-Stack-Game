package co.com.sofka.mongo.card;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.card.gateways.CardRepository;
import co.com.sofka.model.player.Player;
import co.com.sofka.model.player.gateways.PlayerRepository;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

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
