package guru.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.websocket.server.ServerEndpoint;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngrediantCommand {
    private Long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand unitOfMeasureCommand;
}
