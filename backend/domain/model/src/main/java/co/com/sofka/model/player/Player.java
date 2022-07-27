package co.com.sofka.model.player;

import co.com.sofka.model.card.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Modelo Player con sus atributos
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Player {
    private String id;
    private Integer score;
    private Set<Card> cards;
    private Boolean turn;
    private String user;


}
