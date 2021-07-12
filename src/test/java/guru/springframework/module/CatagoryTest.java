package guru.springframework.module;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

// by entering alt+enter on the name class you can create a module to test the members of
// the class .
public class CatagoryTest {
    Catagory catagory;

    // by before annotation we are saying run this method before other test method
    // being executed .
    @Before
    public void setUp() {
        catagory = new Catagory();
    }

    @Test
    public void getId() {
        //assert is a static method that's why you should use the owner class name to evoke it.
        Long idValue = 4L;
        catagory.setId(idValue);
        Assert.assertEquals(idValue, catagory.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipe() {
    }
}