package co.com.sofka.mongo.board;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.player.Player;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Documento para el modelo Board
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Data
@Document(value = "board")
public class BoardDocument {
    @Id
    private String id;
    private Map<String, Card> cardsBetPlayers;
    private Map<Integer, Player> turn;
    private Player winnerRound;
}
