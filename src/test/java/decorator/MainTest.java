package decorator;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;


public class MainTest extends TestCase {

    public void testMain() {
        Document document = new CachedDocument("gs://oop-course/Geico-2021.png");
        Document document1 = new TimedDocument("gs://oop-course/Geico-2021.png");
        String ans = document.parse();
        String ans1 = document1.parse();
        Assertions.assertTrue(ans.length() > 0);
        Assertions.assertTrue(ans1.length() > 0);
    }
}