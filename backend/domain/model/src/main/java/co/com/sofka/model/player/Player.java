package co.com.sofka.model.player;
import co.com.sofka.model.card.Card;
import co.com.sofka.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Player {
    private String id;
    private Integer score;
    private Set<Card> cards;
    private User user;

}
