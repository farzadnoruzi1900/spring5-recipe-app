package guru.springframework.module;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(exclude = {"recipe"})
public class Ingerediant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String description;
    private BigDecimal amount;



    @ManyToOne
    //@JoinColumn(name = "recipe_id")
    //simple definition is that it tells JPA how to do the mapping and how these two entities are gonna
    // be related at dataBase level so we have recipe_id properti at the ingrediant level .
    private Recipe recipe;
//    In a One-to-Many/Many-to-One relationship, the owning side is usually
//    defined on the ‘many' side of the relationship. It's usually the side which owns the foreign key.
//
//    The @JoinColumn annotation defines that actual physical mapping on the owning side:
/*Once we have defined the owning side of the relationship, Hibernate already has all the
    information it needs to map that relationship in our database. To make this association
    bidirectional, all we'll have to do is to define the referencing side. The inverse or the
    referencing side simply maps to the owning side.*/
    // as you check the Recipe you will find the mapped by.
    //the one stays in many side has the mapped and other side has the joinColumn

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;
    // by default it is eager for sure but it is good practice to show it to some others working on the code
    // it is retrieving this data everyTime from the data Base .
// and moreOver we have unidirectional relatonship means the unitOfMeasure does not need to have instance of our
    // Ingredient entity .
    public Ingerediant() {
    }

    public Ingerediant(String description, BigDecimal amount,  UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

}
