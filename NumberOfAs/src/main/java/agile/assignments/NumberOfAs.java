package agile.assignments;

/**
 *
 * Counts the number of As in a string ( case sensitive ).
 *
 * @author Tim
 */
public class NumberOfAs {

    public int NumberOfAs(String s) throws Exception {

        if(s.length() > 1000)
            throw new Exception("String is too long.");

        long count = s.chars().filter(ch -> ch == 'A').count();

        return (int) count;
    }

}
