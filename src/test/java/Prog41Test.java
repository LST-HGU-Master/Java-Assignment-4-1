import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.*;

public class Prog41Test {

    @Test
    public void testOutputFormat()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        Prog41.main(new String[]{"1", "2", "3"});

        // assertion
        try {
            assertEquals("1,2,3" + System.lineSeparator(), bos.toString(),
                         "printされた結果が指示と異なります!"
            );
        } catch(AssertionError err) {
            System.setOut(originalOut);
            throw err;
        }

        // undo the binding in System
        System.setOut(originalOut);
    }
}
