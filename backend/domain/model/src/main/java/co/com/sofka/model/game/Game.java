package co.com.sofka.model.game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Game {

    private String id;
    private Integer round;
    private Set<Player> players;
    private Board board;
    private Set<Card> cards;
}
