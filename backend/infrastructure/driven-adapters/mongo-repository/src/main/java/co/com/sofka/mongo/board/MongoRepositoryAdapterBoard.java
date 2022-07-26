package co.com.sofka.mongo.board;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.board.gateways.BoardRepository;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

/**
 * Adaptador del repositorio de Board para las operaciones en la base de datos
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Repository
public class MongoRepositoryAdapterBoard extends AdapterOperations<Board, BoardDocument, String, MongoDBRepositoryBoard>
 implements BoardRepository
{

    public MongoRepositoryAdapterBoard(MongoDBRepositoryBoard repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Board.class));
    }


}
