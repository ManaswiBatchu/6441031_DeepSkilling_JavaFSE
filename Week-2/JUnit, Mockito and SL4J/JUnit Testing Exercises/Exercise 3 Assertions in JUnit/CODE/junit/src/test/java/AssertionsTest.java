import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
public class AssertionsTest {

    @Test
    public void testAssertions() {
        assertEquals(8, 3 + 5);

        assertTrue(7 > 4);

        assertFalse(6 < 2);

        assertNull(null);
        
        assertNotNull(new Object());
    }
}
