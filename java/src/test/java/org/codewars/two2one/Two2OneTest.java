import static org.junit.Assert.*;
import org.junit.Test;

public class Two2OneTest {

    @Test
    public void test() {
        System.out.println("longest Fixed Tests");
        assertEquals("aehrsty", Two2One.longest("aretheyhere", "yestheyarehere"));
        assertEquals("abcdefghilnoprstu", Two2One.longest("loopingisfunbutdangerous", "lessdangerousthancoding"));
        assertEquals("acefghilmnoprstuy", Two2One.longest("inmanylanguages", "theresapairoffunctions"));
    }
}