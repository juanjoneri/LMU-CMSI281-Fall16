import static org.junit.Assert.*;

import javafx.scene.input.InputMethodTextRun;
import org.junit.*;
import org.junit.rules.Timeout;
import sun.awt.image.ImageWatched;

public class LinkedYarnTests {

    // =================================================
    // Test Configuration
    // =================================================

    // Global timeout to prevent infinite loops from
    // crashing the test suite
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    // Used as the basic empty LinkedYarn to test; the @Before
    // method is run before every @Test
    LinkedYarn ball;
    LinkedYarn bola;
    String[] words = {
            "Ohrwurm",
            "Fernweh",
            "Kummerspeck",
            "Schweinehund",
            "Fremdschämen",
            "Torschlusspanik",
            "Treppenwitz",
            "Lebensmüde",
            "Weltschmerz",
            "Weichei",
            "Backpfeifengesicht",
            "Erklärungsnot",
            "Sitzfleisch",
            "Purzelbaum",
            "Dreikäsehoch",
            "Zungenbrecher",
            "Schattenparker",
            "Kuddelmuddel",
            "Kugelschreiber",
            "Kartoffelpuffer"
    };

    @Before
    public void init () {
        ball = new LinkedYarn();
        bola = new LinkedYarn();
    }


    // =================================================
    // Unit Tests
    // =================================================

    // Initialization Tests
    // -------------------------------------------------
    @Test
    public void testInit() {
        assertTrue(ball.isEmpty());
        assertEquals(0, ball.getSize());

        //MY TESTS
        assertEquals(0, ball.getUniqueSize());
        for(String word : words){
            assertFalse(ball.contains(word));
            assertEquals(0, ball.count(word));
            assertTrue(ball.getMostCommon() == null);
        }
    }

    // Basic Tests
    // -------------------------------------------------
    @Test
    public void testIsEmpty() {
        assertTrue(ball.isEmpty());
        ball.insert("not_empty");
        assertFalse(ball.isEmpty());

        //MY TESTS
        ball.remove("not_empty");
        assertTrue(ball.isEmpty());
        ball.insert("not_empty");
        ball.removeAll("not_empty");
        assertTrue(ball.isEmpty());

        LinkedYarn.Iterator iterator = ball.getIterator();
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasPrev());
        assertEquals(null, iterator.getString());
    }

    @Test
    public void testGetSize() {
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(2, ball.getSize());
        ball.insert("unique");
        assertEquals(3, ball.getSize());

        //MY TESTS
        bola = new LinkedYarn();
        assertEquals(0, bola.getSize());
        for(String word : words){
            bola.insert(word);
        }
        assertEquals(words.length, bola.getSize());

        bola = new LinkedYarn();
        int repetitions = 100;
        for (int i = 0; i < repetitions; i ++) {
            for (String word : words) {
                bola.insert(word);
            }
        }
        assertEquals(words.length * repetitions, bola.getSize());

        bola = new LinkedYarn();
        for (int i = 0; i < repetitions; i ++) {
            bola.insert(words[(int) (Math.random()*words.length)]);
            assertEquals(i + 1, bola.getSize());
        }
        assertEquals(repetitions, bola.getSize());
    }

    @Test
    public void testGetUniqueSize() {
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(1, ball.getUniqueSize());
        ball.insert("unique");
        assertEquals(2, ball.getUniqueSize());

        //MY TESTS
        bola = new LinkedYarn();
        assertEquals(0, bola.getUniqueSize());
        for(String word : words){
            bola.insert(word);
        }
        assertEquals(words.length, bola.getUniqueSize());

        bola = new LinkedYarn();
        int repetitions = 100;
        for (int i = 0; i < repetitions; i ++) {
            for (String word : words) {
                bola.insert(word);
            }
        }
        assertEquals(words.length, bola.getUniqueSize());

        for (int i = 0; i < repetitions; i++ ) {
            bola = new LinkedYarn();
            for (int j = 0; j < repetitions; j++) {
                bola.insert(words[(int) (Math.random() * words.length)]);
            }
            assertTrue(words.length >= bola.getUniqueSize());
        }

    }

    // LinkedYarn Manipulation Tests
    // -------------------------------------------------
    @Test
    public void testInsert() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));

        //MY TESTS
        
    }

    @Test
    public void testRemove() {
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(2, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
        int dups = ball.remove("dup");
        assertEquals(1, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
        assertEquals(1, dups);
    }

    @Test
    public void testRemoveAll() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(3, ball.getSize());
        assertEquals(2, ball.getUniqueSize());
        ball.removeAll("dup");
        assertEquals(1, ball.getSize());
        assertEquals(1, ball.getUniqueSize());
    }

    @Test
    public void testCount() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(2, ball.count("dup"));
        assertEquals(1, ball.count("unique"));
        assertEquals(0, ball.count("forneymon"));
    }

    @Test
    public void testContains() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertFalse(ball.contains("forneymon"));
    }
    // This is tested pretty much everywhere so...


    @Test
    public void testGetMostCommon() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        assertEquals("dup", ball.getMostCommon());
        ball.insert("cool");
        String mc = ball.getMostCommon();
        assertTrue(mc.equals("dup") || mc.equals("cool"));
    }

    // Iterator Tests
    // -------------------------------------------------
    @Test
    public void testIteratorBasics() {
        ball.insert("a");
        ball.insert("a");
        ball.insert("a");
        ball.insert("b");
        LinkedYarn.Iterator it = ball.getIterator();

        // Test next()
        LinkedYarn dolly = ball.clone();
        while (true) {
            String gotten = it.getString();
            assertTrue(dolly.contains(gotten));
            dolly.remove(gotten);
            if (it.hasNext()) {it.next();} else {break;}
        }
        assertTrue(dolly.isEmpty());
        assertFalse(it.hasNext());

        // Test prev()
        dolly = ball.clone();
        while (true) {
            String gotten = it.getString();
            assertTrue(dolly.contains(gotten));
            dolly.remove(gotten);
            if (it.hasPrev()) {it.prev();} else {break;}
        }
        assertTrue(dolly.isEmpty());
        assertFalse(it.hasPrev());

        int countOfReplaced = ball.count(it.getString());
        it.replaceAll("replaced!");
        assertEquals(countOfReplaced, ball.count("replaced!"));
        assertTrue(it.isValid());

        ball.insert("c");
        assertFalse(it.isValid());
    }

    // Inter-LinkedYarn Tests
    // -------------------------------------------------
    @Test
    public void testClone() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        LinkedYarn dolly = ball.clone();
        assertEquals(2, dolly.count("dup"));
        assertEquals(1, dolly.count("unique"));
        dolly.insert("cool");
        assertFalse(ball.contains("cool"));
    }

    @Test
    public void testSwap() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("yo");
        y2.insert("sup");
        y1.swap(y2);
        assertTrue(y1.contains("yo"));
        assertTrue(y1.contains("sup"));
        assertTrue(y2.contains("dup"));
        assertTrue(y2.contains("unique"));
        assertFalse(y1.contains("dup"));
    }

    // Static Method Tests
    // -------------------------------------------------
    @Test
    public void testKnit() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("dup");
        y2.insert("cool");
        LinkedYarn y3 = LinkedYarn.knit(y1, y2);
        assertEquals(3, y3.count("dup"));
        assertEquals(1, y3.count("unique"));
        assertEquals(1, y3.count("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
    }

    @Test
    public void testTear() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("dup");
        y2.insert("cool");
        LinkedYarn y3 = LinkedYarn.tear(y1, y2);
        assertEquals(1, y3.count("dup"));
        assertEquals(1, y3.count("unique"));
        assertFalse(y3.contains("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));
    }

    @Test
    public void testSameYarn() {
        LinkedYarn y1 = new LinkedYarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        LinkedYarn y2 = new LinkedYarn();
        y2.insert("unique");
        y2.insert("dup");
        y2.insert("dup");
        assertTrue(LinkedYarn.sameYarn(y1, y2));
        assertTrue(LinkedYarn.sameYarn(y2, y1));
        y2.insert("test");
        assertFalse(LinkedYarn.sameYarn(y1, y2));
    }

}
