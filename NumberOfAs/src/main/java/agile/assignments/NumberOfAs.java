package agile.assignments;

public class NumberOfAs {

    public int NumberOfAs(String s) throws Exception {

        if(s.length() > 1000)
            throw new Exception("String is too long.");

        long count = s.chars().filter(ch -> ch == 'A').count();

        return (int) count;
    }

}
