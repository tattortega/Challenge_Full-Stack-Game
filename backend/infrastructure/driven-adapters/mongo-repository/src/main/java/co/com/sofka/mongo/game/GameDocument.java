package co.com.sofka.mongo.game;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Data
@Document(value = "games")
public class GameDocument {
    @Id
    private String id;
    private Integer round;
    private Set<Player> players;
    private Board board;
    private List<Card> cards;
}
