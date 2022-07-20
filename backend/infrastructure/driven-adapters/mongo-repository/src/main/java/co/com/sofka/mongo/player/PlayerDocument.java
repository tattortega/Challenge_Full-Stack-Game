package co.com.sofka.mongo.player;

import co.com.sofka.model.board.Board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.player.Player;
import co.com.sofka.model.user.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(value = "player")
public class PlayerDocument {
    @Id
    private String id;
    private Integer score;
    private Set<Card> cards;
    private User user;
}
