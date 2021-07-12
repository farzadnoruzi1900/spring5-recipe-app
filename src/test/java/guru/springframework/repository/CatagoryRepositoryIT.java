package guru.springframework.repository;

import guru.springframework.module.UnitOfMeasure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
//this annotation will bring up an embedded database and configure spring data jpa for us
@DataJpaTest
public class CatagoryRepositoryIT {
/*this is supposed to be integration test thats why we use IT at the end of the name..*/

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;
    // by saying autowired the spring will inject it automatically in our environment .
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() {
        Optional<UnitOfMeasure> unitOPt =unitOfMeasureRepository.findByDescription("Tablespoon");
        Assert.assertEquals("Tablespoon",unitOPt.get().getDescription());
    }
}