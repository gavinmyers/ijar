package ijar;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class IjarTest {
    @Test
    public void testTriple() {
        assertThat(Main.triple("AB"), equalTo("ABABAB"));
    }
}
