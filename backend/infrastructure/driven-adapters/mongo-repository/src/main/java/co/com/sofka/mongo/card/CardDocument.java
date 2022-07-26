package co.com.sofka.mongo.card;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Documento para el modelo Card
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
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
