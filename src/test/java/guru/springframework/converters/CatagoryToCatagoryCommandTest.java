package guru.springframework.converters;

import guru.springframework.commands.CatagoryCommand;
import guru.springframework.module.Catagory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CatagoryToCatagoryCommandTest {
    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "descript";
    CatagoryToCatagoryCommand convter;

    @Before
    public void setUp() throws Exception {
        convter = new CatagoryToCatagoryCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(convter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(convter.convert(new Catagory()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Catagory category = new Catagory();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CatagoryCommand categoryCommand = convter.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());

    }

}