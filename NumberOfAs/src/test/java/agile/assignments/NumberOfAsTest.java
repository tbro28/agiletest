package agile.assignments;

//import org.junit.Test;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * Test cases for the NumberOfAs class.
 *
 * @author Tim
 */
public class NumberOfAsTest {

    /**
     * Count the number of uppercase As.
     */
    @Test
    public void CountNumberOfUpperCaseAsTest() throws Exception {

        NumberOfAs num = new NumberOfAs();

        assertEquals(3, num.NumberOfAs("AAA"));
    }

    /**
     * With only lowercase the result should be zero.
     */
    @Test
    public void CountNumberOfLowerCaseAsOnlyIsZeroTest() throws Exception {

        NumberOfAs num = new NumberOfAs();

        assertEquals(0, num.NumberOfAs("aaa"));
    }

    /**
     * With a mix of upper and lower case As the count should be
     * just the uppercase A characters.
     */
    @Test
    public void CountNumberOfUpperAndLowerCaseAsTest() throws Exception {

        NumberOfAs num = new NumberOfAs();

        assertEquals(6, num.NumberOfAs("AaAaAaaaAAA"));
    }

    /**
     * A string with no As should return zero.
     */
    @Test
    public void CountOfUpperCaseAsWithNoneTest() throws Exception {

        NumberOfAs num = new NumberOfAs();

        assertEquals(0, num.NumberOfAs("abcdefghijklmnopqrstuvwxyz" +
                "BCDEFGHIJKLMNOPQRSTUVWXYZ"));
    }

    /**
     * An alphanumeric string with As should return the correct count.
     */
    @Test
    public void CountOfUpperCaseAsWithAlphaNumericTest() throws Exception {

        NumberOfAs num = new NumberOfAs();

        assertEquals(4, num.NumberOfAs("aaa123AAA000A"));
    }

    /**
     * An alphanumeric string with As at the beginning and end
     * of the string should return the correct count.
     */
    @Test
    public void CountOfUpperCaseAsAtTheStringBeginningAndEndTest() throws Exception {

        NumberOfAs num = new NumberOfAs();

        assertEquals(3, num.NumberOfAs("Aaaa123A000A"));
    }

    /**
     * A string with special characters should return the correct count.
     */
    @Test
    public void CountOfUpperCaseAsWithSpecialCharactersTest() throws Exception {

        NumberOfAs num = new NumberOfAs();

        assertEquals(4, num.NumberOfAs("*.@#$!^&%*(A)_`~Aaaa123A000A*"));
    }

    /**
     * An empty string should return zero.
     */
    @Test
    public void CountOfUpperCaseAsWithAnEmptyStringTest() throws Exception {

        NumberOfAs num = new NumberOfAs();

        assertEquals(0, num.NumberOfAs(""));
    }

    /**
     * A string over 1000 characters is not counted.
     */
    @Test
    public void CountOfUpperCaseAsWithOverMaxStringLength() throws Exception {

        NumberOfAs num = new NumberOfAs();
        String testStr = "12345678901234567890123456789012345678901234567890";

        for(int i = 0; i < 20; i++)
            testStr += testStr;

        testStr += "1";

        String finalTestStr = testStr;

        assertThrows(Exception.class, () -> num.NumberOfAs(finalTestStr));
    }

}
