package guru.springframework.module;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingerediant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String description;
    private BigDecimal amount;
    @ManyToOne
    //@JoinColumn(name = "recipe_id")
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

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;
    // by default it is eager for sure but it is good practice to show it to some others working on the code
    // it is retrieving this data everyTime from the data Base .
// and moreOver we have unidirectional relatonship means the unitOfMeasure does not need to have instance of our
    // Ingredient entity .
    public Ingerediant() {
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

}
