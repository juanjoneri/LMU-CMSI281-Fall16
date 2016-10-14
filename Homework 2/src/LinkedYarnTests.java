import static org.junit.Assert.*;

import javafx.concurrent.Worker;
import javafx.scene.input.InputMethodTextRun;
import org.junit.*;
import org.junit.rules.Timeout;
import sun.awt.image.ImageWatched;

import javax.print.DocFlavor;

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
    int repetitions = 100;
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

        bola = new LinkedYarn();
        for(String word : words) {
            for (int i = 0; i < (int) (Math.random()*repetitions); i++) {
                bola.insert(word);
            }
        }
        assertEquals(words.length, bola.getUniqueSize());

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
        bola = new LinkedYarn();
        for(String word : words){
            bola.insert(word);
            assertTrue(bola.contains(word));
        }


        bola = new LinkedYarn();
        for (int i = 0; i < repetitions; i ++) {
            for (String word : words) {
                bola.insert(word);
            }
        }
        for(String word : words){
            assertTrue(bola.contains(word));
            assertFalse(bola.contains(word + "!"));
        }

        bola = new LinkedYarn();
        for (int i = 0; i < repetitions; i ++) {
            bola.insert(words[(int) (Math.random()*words.length)]);
        }
        int count = 0;
        for(String word : words) {
            count += bola.contains(word) ? 1 : 0;
        }
        assertTrue(count <= words.length);

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

        //MY TESTS
        bola = new LinkedYarn();
        for(String word : words){
            bola.insert(word);
            bola.remove(word);
            assertFalse(bola.contains(word));
            assertEquals(0, bola.count(word));
            assertTrue(bola.isEmpty());
            assertEquals(0, bola.getUniqueSize());
            assertEquals(0, bola.getSize());
        }

        for (String word : words) {
            bola.insert(word);
        }
        for (int i = 0; i < words.length; i ++) {
            bola.remove(words[i]);
            assertEquals(words.length - i -1, bola.getSize());
            assertEquals(words.length - i -1, bola.getUniqueSize());
        }

        for (String word : words) {
            bola.remove(word);
            assertEquals(0, bola.getSize());
            assertEquals(0, bola.getUniqueSize());
        }
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

        //MY TESTS
        bola = new LinkedYarn();
        for(String word : words){
            for( int i = 0; i < repetitions; i ++) {
                bola.insert(word);
            }
            bola.remove(word);
            assertTrue(bola.contains(word));
            assertEquals(repetitions - 1, bola.count(word));
            assertFalse(bola.isEmpty());
            assertEquals(1, bola.getUniqueSize());
            assertEquals(repetitions - 1, bola.getSize());
            bola.removeAll(word);
            assertFalse(bola.contains(word));
            assertEquals(0, bola.count(word));
            assertTrue(bola.isEmpty());
            assertEquals(0, bola.getUniqueSize());
            assertEquals(0, bola.getSize());
        }

        for(String word : words) {
            for (int i = 0; i < (int) (Math.random()*repetitions); i++) {
                bola.insert(word);
            }
        }
        assertEquals(words.length, bola.getUniqueSize());
        for( int i = 0; i < repetitions; i ++ ){
            bola.removeAll(words[(int) (Math.random()*words.length)]);
        }
        assertEquals(0, bola.getUniqueSize());
        assertEquals(0, bola.getSize());
    }

    @Test
    public void testCount() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(2, ball.count("dup"));
        assertEquals(1, ball.count("unique"));
        assertEquals(0, ball.count("forneymon"));

        //MY TESTS - this one is nice..
        bola = new LinkedYarn();
        for(String word : words) {
            assertEquals(0, bola.count(word));
            int i = 0;
            while (i < (int) (Math.random()*repetitions)) {
                bola.insert(word);
                i ++;
            }
            assertEquals(i, bola.count(word));
            bola.remove(word);
            if (i > 0) {
                assertEquals(i - 1, bola.count(word));
            }
            bola.removeAll(word);
            assertEquals(0, bola.count(word));
        }
    }

    @Test
    public void testContains() {
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertFalse(ball.contains("forneymon"));

        //MY TESTS
        bola = new LinkedYarn();
        for (String word : words) {
            assertFalse(bola.contains(word));
            int i = 0;
            while (i < (int) (Math.random() * repetitions)) {
                bola.insert(word);
                i++;
            }
            if (i > 0) {
                assertTrue(bola.contains(word));
            }
            bola.remove(word);
            if (i > 0) {
                assertTrue(bola.contains(word));
            }
            bola.removeAll(word);
            assertFalse(bola.contains(word));
        }
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

        //MY TESTS - very unlikely but it could go bad by chance..
        bola = new LinkedYarn();
        int count = 1;
        for(String word : words){
            for(int i = 0; i < count; i ++){
                bola.insert(word);
            }
            assertEquals(word, bola.getMostCommon());
            count ++;
        }
        for(int i = 0; i < words.length - 1; i ++){
            assertEquals(words[words.length - 1], bola.getMostCommon());
        }

        bola = new LinkedYarn();
        String mostCommon = null;
        int maxInsertions = 0;
        for(String word : words){
            int insertions = (int) (Math.random()*repetitions);
            maxInsertions = maxInsertions > insertions ? maxInsertions : insertions;
            mostCommon = maxInsertions <= insertions ? word : mostCommon;
            for (int i =0; i < insertions; i ++) {
                bola.insert(word);
            }
            assertEquals(mostCommon, bola.getMostCommon());
        }

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

        //MY TESTS
        bola = new LinkedYarn();
        LinkedYarn.Iterator iterator = bola.getIterator();
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasPrev());

        for(String word : words){
            bola.insert(word);
        }
        assertFalse(iterator.isValid());
        iterator = bola.getIterator();
        for(int i = 0; i < words.length - 1; i ++){
            assertTrue(iterator.hasNext());
            iterator.next();
        }
        assertFalse(iterator.hasNext());
        for(int i = 0; i < words.length - 1; i ++){
            assertTrue(iterator.hasPrev());
            iterator.prev();
        }
        assertTrue(iterator.isValid());
        assertFalse(iterator.hasPrev());

        bola = new LinkedYarn();
        for(int i = 0; i < repetitions; i++){
            bola.insert(words[(int) (Math.random()*words.length)]);
        }
        iterator = bola.getIterator();
        for(int i = 0; i < repetitions - 1; i ++){
            assertTrue(iterator.hasNext());
            assertTrue(iterator.isValid());
            iterator.next();
        }
        assertFalse(iterator.hasNext());
        for(int i = 0; i < repetitions - 1; i ++){
            assertTrue(iterator.hasPrev());
            assertTrue(iterator.isValid());
            iterator.prev();
        }
        assertFalse(iterator.hasPrev());
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

        //MY TESTS
        bola = new LinkedYarn();
        LinkedYarn bola2 = bola.clone();
        assertTrue(bola2.isEmpty());
        bola.insert("Space Inviders");
        assertTrue(bola2.isEmpty());
        bola2.insert("Cultural Activity");
        assertFalse(bola.contains("Cultural Activity"));

        bola = new LinkedYarn();
        for(String word : words){
            bola.insert(word);
            bola2 = bola.clone();
            assertTrue(bola.contains(word));
            assertTrue(bola2.contains(word));
        }

        bola = new LinkedYarn();
        for(int i = 0; i < repetitions; i++){
            bola.insert(words[(int) (Math.random()*words.length)]);
            bola2 = bola.clone();
            assertTrue(LinkedYarn.sameYarn(bola, bola2));
        }

        for(String word : words){
            bola.remove(word);
        }
        assertFalse(LinkedYarn.sameYarn(bola, bola2));

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

        //MY TESTS
        bola = new LinkedYarn();
        for(int i = 0; i < repetitions; i++){
            bola.insert(words[(int) (Math.random()*words.length)]);
        }

        LinkedYarn bola2 = new LinkedYarn();
        for(int i = 0; i < repetitions; i++){
            bola2.insert(words[(int) (Math.random()*words.length)]);
        }
        assertFalse(LinkedYarn.sameYarn(bola,bola2));

        LinkedYarn bolaClone = bola.clone();
        LinkedYarn bola2Clone = bola2.clone();


        assertFalse(LinkedYarn.sameYarn(bola,bola2Clone));
        assertFalse(LinkedYarn.sameYarn(bola2,bolaClone));
        bola.swap(bola2);
        assertFalse(LinkedYarn.sameYarn(bola,bola2));
        assertFalse(LinkedYarn.sameYarn(bolaClone,bola2Clone));

        assertTrue(LinkedYarn.sameYarn(bola,bola2Clone));
        assertTrue(LinkedYarn.sameYarn(bola2,bolaClone));
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

        //MY TESTS
        bola = new LinkedYarn();
        LinkedYarn bola2 = new LinkedYarn();

        for(int i = 0; i < repetitions; i++){
            bola.insert(words[(int) (Math.random()*words.length)]);
            bola2.insert(words[(int) (Math.random()*words.length)]);
        }

        LinkedYarn bola3 = LinkedYarn.knit(bola, bola2);
        for (String word : words){
            assertEquals(bola.count(word) + bola2.count(word), bola3.count(word));
        }

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

        //MY TESTS
        bola = new LinkedYarn();
        LinkedYarn bola2 = new LinkedYarn();

        for(int i = 0; i < repetitions; i++){
            bola.insert(words[(int) (Math.random()*words.length)]);
            bola2.insert(words[(int) (Math.random()*words.length)]);
        }

        LinkedYarn bola3 = LinkedYarn.tear(bola, bola2);
        for (String word : words){
            int diff = Math.max( 0, bola.count(word) - bola2.count(word) );
            assertEquals(diff, bola3.count(word));
        }

        bola = new LinkedYarn();
        bola2 = new LinkedYarn();
        bola3 = LinkedYarn.tear(bola, bola2);
        assertTrue(bola3.isEmpty());
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

        //MY TESTS
        bola = new LinkedYarn();
        LinkedYarn bola2 = new LinkedYarn();
        assertTrue(LinkedYarn.sameYarn(bola, bola2));

        for (String word : words){
            int index = (int) (Math.random()*words.length);
            bola.insert(words[index]);
            bola2.insert(words[index]);
        }
        assertTrue(LinkedYarn.sameYarn(bola, bola2));

        for (String word : words){
            int index = (int) (Math.random()*words.length/2);
            bola.remove(words[index]);
            bola2.remove(words[index]);
        }
    }

}
