package guru.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CatagoryCommand {
    // it is good to notice that there is not any need to add bidirectional properties because
    // we are not doing mapping and working with jpa here so we just need the simple pojo and it's files
    // no need to have for instance recipe collection here . this is important matter.
    private Long id;
    private String description;

}
