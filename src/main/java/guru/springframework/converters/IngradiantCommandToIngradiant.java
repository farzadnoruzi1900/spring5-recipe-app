package guru.springframework.converters;

import guru.springframework.commands.IngrediantCommand;
import guru.springframework.module.Ingerediant;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngradiantCommandToIngradiant implements Converter<IngrediantCommand, Ingerediant> {

    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    public IngradiantCommandToIngradiant(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingerediant convert(IngrediantCommand source) {
        if(source==null){
            return null;
        }
        Ingerediant ingerediant=new Ingerediant();
        ingerediant.setAmount(source.getAmount());
        ingerediant.setDescription(source.getDescription());
        ingerediant.setId(source.getId());
        ingerediant.setUnitOfMeasure(unitOfMeasureCommandToUnitOfMeasure.convert(source.getUnitOfMeasureCommand()));
        return ingerediant;
    }
}
