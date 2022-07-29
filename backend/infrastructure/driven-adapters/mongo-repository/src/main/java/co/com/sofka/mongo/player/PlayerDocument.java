package co.com.sofka.mongo.player;

import co.com.sofka.model.card.Card;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * Documento para el modelo Player
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Data
@Document(value = "player")
public class PlayerDocument {
    @Id
    private String id;
    private String name;
    private Integer score;
    private Set<Card> cards;
    private Boolean turn;
    private String user;
}
