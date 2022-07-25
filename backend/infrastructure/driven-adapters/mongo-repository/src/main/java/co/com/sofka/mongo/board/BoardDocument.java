package co.com.sofka.mongo.board;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.player.Player;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

@Data
@Document(value = "board")
public class BoardDocument {
    @Id
    private String id;
    private Map<String, Card> cardsBetPlayers;
    private Map<Integer, Player> turn;
    private Player winnerRound;
}
