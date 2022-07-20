package co.com.sofka.mongo.game;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.player.Player;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(value = "games")
public class GameDocument {
    @Id
    private String id;
    private Integer round;
    private Set<Player> players;
    private Board board;
    private Set<Card> cards;
}
