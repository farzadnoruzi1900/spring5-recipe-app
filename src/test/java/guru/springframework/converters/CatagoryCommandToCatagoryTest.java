package guru.springframework.converters;

import guru.springframework.commands.CatagoryCommand;
import guru.springframework.module.Catagory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CatagoryCommandToCatagoryTest {


    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "description";
    CatagoryCommandToCatagory conveter;

    @Before
    public void setUp() throws Exception {
        conveter = new CatagoryCommandToCatagory();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(conveter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(conveter.convert(new CatagoryCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        CatagoryCommand categoryCommand = new CatagoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Catagory category = conveter.convert(categoryCommand);

        //then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}