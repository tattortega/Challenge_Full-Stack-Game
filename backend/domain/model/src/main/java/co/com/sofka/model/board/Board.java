package co.com.sofka.model.board;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


/**
 * Modelo Board con sus atributos
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Board {

    private String id;
    private Map<String, Card> cardsBetPlayers;
    private Map<Integer, Player> turn;
    private Player winnerRound;
}
