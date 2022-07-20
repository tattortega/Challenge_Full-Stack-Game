package co.com.sofka.model.card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class Card {

    private String id;
    private String description;
    private Integer power;
    private Boolean status;
    private String image;
    private String feature;

}
