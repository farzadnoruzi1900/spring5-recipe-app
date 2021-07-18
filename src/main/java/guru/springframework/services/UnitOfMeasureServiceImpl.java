package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitofMeasureToUnitOfMeasureCommand;
import guru.springframework.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitofMeasureToUnitOfMeasureCommand uomConvertor;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                      UnitofMeasureToUnitOfMeasureCommand uomConvertor) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.uomConvertor = uomConvertor;
    }
/*
    an extra explanation about this method is that the streamsupporter is specifically
    for preparing environment ot treat an iterable like stream
            StreamSupport.stream(iterable.spliterator(), false);*/
    @Override
    public Set<UnitOfMeasureCommand> getAllUom() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
        .spliterator(),false)
                .map(uomConvertor::convert)
                .collect(Collectors.toSet());

    }
}
