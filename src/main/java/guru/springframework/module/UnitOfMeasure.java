package guru.springframework.module;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String description;


    public UnitOfMeasure() {
    }



}
