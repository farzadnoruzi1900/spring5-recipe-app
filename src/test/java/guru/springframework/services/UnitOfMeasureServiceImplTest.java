package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitofMeasureToUnitOfMeasureCommand;
import guru.springframework.module.UnitOfMeasure;
import guru.springframework.repository.UnitOfMeasureRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceImplTest {
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    UnitOfMeasureService unitOfMeasureService;
    UnitofMeasureToUnitOfMeasureCommand unitofMeasureToUnitOfMeasureCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        unitofMeasureToUnitOfMeasureCommand=new UnitofMeasureToUnitOfMeasureCommand();
        unitOfMeasureService=new UnitOfMeasureServiceImpl(unitOfMeasureRepository,unitofMeasureToUnitOfMeasureCommand);

    }

    @Test
    public void getAllUom() {
        Set<UnitOfMeasure> unitOfMeasureCommandSet=new HashSet<>();
        UnitOfMeasure unitOfMeasureCommand=new UnitOfMeasure();
        unitOfMeasureCommand.setId(1l);
        UnitOfMeasure unitOfMeasureCommand1=new UnitOfMeasure();
        unitOfMeasureCommand1.setId(2l);
        unitOfMeasureCommandSet.add(unitOfMeasureCommand);
        unitOfMeasureCommandSet.add(unitOfMeasureCommand1);
        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasureCommandSet);

        Set<UnitOfMeasureCommand> unitOfMeasureCommandSet1=unitOfMeasureService.getAllUom();

        Assert.assertEquals(2,unitOfMeasureCommandSet1.size());

        verify(unitOfMeasureRepository).findAll();
    }
}