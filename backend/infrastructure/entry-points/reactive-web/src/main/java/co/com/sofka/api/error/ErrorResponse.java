package co.com.sofka.api.error;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Modelo para los mensajes de error al response
 *
 * @author Jhon Edward Acevedo <jhedacro@gmail.com>
 * @author Oscar Gabriel Farfan <oscarfarfan92@gmail.com>
 * @author Luis Ricardo Ortega <tattortega.28@gmail.com>
 * @version 1.0.0 2022-07-26
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String name;
    private String message;
    private int status;
}
