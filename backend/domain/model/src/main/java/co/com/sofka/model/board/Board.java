package co.com.sofka.model.board;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Board {

    private String id;
    private Map<String, Card> cardsBetPlayers;
    private Map<Integer, Player> turn;
}
