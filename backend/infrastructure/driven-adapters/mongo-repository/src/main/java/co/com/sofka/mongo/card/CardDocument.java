package co.com.sofka.mongo.card;

import co.com.sofka.model.card.Card;
import co.com.sofka.model.user.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(value = "cards")
public class CardDocument {
    @Id
    private String id;
    private String description;
    private Integer power;
    private Boolean status;
    private String image;
    private String feature;
}
