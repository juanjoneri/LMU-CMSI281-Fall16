/**
 * Tests for the Yarn class
 *
 * In addition to the Unit Tests that are found in the following section, add at least 1 Unit Test for each method and static method,
 * or expand each of the ones given in the next section.
 *
 * Note: you do not have to use the Eclipse JUnit test suite format for this part of the assignment.
 * Simply submit a file called YarnTests.java with your assignment that contains your unit tests, in whatever format they may be.
 *
 * Add a comment to your test suite indicating each unit test that revealed a bug in your program!
 */

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class YarnTests {

    String[] strings = {
            "Antígona",
            "Anaxágoras",
            "ópalo",
            "endócrino",
            "héroe",
            "cómpralo",
            "ídolo",
            "órdenes",
            "déficit",
            "esdrújula",
            "espátula",
            "éxtasis",
            "tarántula",
            "páncreas",
            "océano",
            "fantástico",
            "caótico",
            "término",
            "cárceles",
            "carátula"};

    @Test
    public void testYarn() {
        Yarn ball = new Yarn();

        // ## MY CODE ##
        for(int i = 0; i < 150; i++){
            ball.insert(strings[(int) (Math.random()*strings.length)]);
        }
    }

    @Test
    public void testIsEmpty() {
        Yarn ball = new Yarn();
        assertTrue(ball.isEmpty());
        ball.insert("not_empty");
        assertFalse(ball.isEmpty());

        // ## MY CODE ##
        ball = new Yarn();
        for(int i = 0; i < 10; i ++) {
            ball.insert(strings[i]);
        }
        assertFalse(ball.isEmpty());

        for(int i = 0; i < 10; i ++) {
            ball.remove(strings[i]);
        }
        assertTrue(ball.isEmpty());

        for(int i = 0; i < 150; i++){
            ball.insert(strings[(int) (Math.random()*strings.length)]);
        }
        assertFalse(ball.isEmpty());

        for(int i = 0; i < strings.length; i ++){
            ball.removeAll(strings[i]);
        }
        assertTrue(ball.isEmpty());


    }

    @Test
    public void testGetSize() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getSize(), 2);
        ball.insert("unique");
        assertEquals(ball.getSize(), 3);

        // ## MY CODE ##
        ball = new Yarn();
        for(int i = 0; i < 10; i ++) {
            ball.insert(strings[i]);
        }
        assertEquals(ball.getSize(), 10);

        for(int i = 0; i < 10; i ++) {
            ball.remove(strings[i]);
        }
        assertEquals(ball.getSize(), 0);

        for(int i = 0; i < 150; i++){
            ball.insert(strings[(int) (Math.random()*strings.length)]);
        }
        assertEquals(ball.getSize(), 100);
    }

    @Test
    public void testGetUniqueSize() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getUniqueSize(), 1);
        ball.insert("unique");
        assertEquals(ball.getUniqueSize(), 2);

        // ## MY CODE ##
        ball = new Yarn();
        for(int i = 0; i < 10; i ++) {
            ball.insert(strings[i]);
        }
        assertEquals(ball.getUniqueSize(), 10);
        for(int i = 0; i < 10; i ++) {
            ball.remove(strings[i]);
        }
        assertEquals(ball.getUniqueSize(), 0);
        for(int i = 0; i < 150; i++){
            ball.insert(strings[i%20]);
        }
        assertEquals(ball.getUniqueSize(), strings.length);
        ball = new Yarn();
        for(int i = 0; i < 150; i++){
            ball.insert(strings[0]);
        }
        assertEquals(ball.getUniqueSize(), 1);
    }

    @Test
    public void testInsert() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));

        // ## MY CODE ##
        ball = new Yarn();
        for(int i = 0; i < 10; i ++) {
            ball.insert(strings[i]);
        }
        for(int i = 0; i < 10; i ++) {
            assertTrue(ball.contains(strings[i]));
        }
        ball = new Yarn();
        for(int i = 0; i < 150; i++){
            ball.insert(strings[i%20]);
        }
        assertFalse(ball.insert(strings[0]));
        for(int i = 0; i < 20; i ++) {
            assertTrue(ball.contains(strings[i]));
        }
        ball = new Yarn();
        for(int i = 0; i < 100; i ++){
            assertTrue(ball.insert(strings[0]));
        }
        assertTrue(ball.contains(strings[0]));
    }

    @Test
    public void testRemove() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        assertEquals(ball.getSize(), 2);
        assertEquals(ball.getUniqueSize(), 1);
        ball.remove("dup");
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(), 1);

        // ## MY CODE ##
        ball = new Yarn();
        for(int i = 0; i < 10; i ++) {
            ball.insert(strings[i]);
        }
        for(int i = 0; i < 10; i ++) {
            ball.remove(strings[i]);
        }
        for(int i = 0; i < 10; i ++) {
            assertFalse(ball.contains(strings[i]));
        }
        ball = new Yarn();
        for(int i = 0; i < 150; i++){
            ball.insert(strings[i%20]);
        }
        for(int i = 0; i < 20; i ++) {
            ball.remove(strings[i]);
        }
        for(int i = 0; i < 20; i ++) {
            assertTrue(ball.contains(strings[i]));
        }
    }

    @Test
    public void testRemoveAll() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(ball.getSize(), 3);
        assertEquals(ball.getUniqueSize(), 2);
        ball.removeAll("dup");
        assertEquals(ball.getSize(), 1);
        assertEquals(ball.getUniqueSize(), 1);

        // ## MY CODE ##
        ball = new Yarn();
        for(int i = 0; i < 10; i ++) {
            ball.insert(strings[i]);
        }
        for(int i = 0; i < 10; i ++) {
            ball.removeAll(strings[i]);
        }
        for(int i = 0; i < 10; i ++) {
            assertFalse(ball.contains(strings[i]));
        }
        ball = new Yarn();
        for(int i = 0; i < 150; i++){
            ball.insert(strings[i%20]);
        }
        for(int i = 0; i < 20; i ++) {
            ball.removeAll(strings[i]);
        }
        for(int i = 0; i < 100; i ++) {
            assertFalse(ball.contains(strings[i%20]));
        }
    }

    @Test
    public void testCount() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertEquals(ball.count("dup"), 2);
        assertEquals(ball.count("unique"), 1);
        assertEquals(ball.count("forneymon"), 0);

        // ## MY CODE ##
        ball = new Yarn();
        for(int i = 0; i < 10; i ++) {
            ball.insert(strings[i]);
        }
        for(int i = 0; i < 10; i ++) {
            assertTrue(ball.count(strings[i]) == 1);
        }
        for(int i = 0; i < 10; i ++) {
            ball.insert(strings[i]);
        }
        for(int i = 0; i < 10; i ++) {
            assertTrue(ball.count(strings[i]) == 2);
        }
        ball = new Yarn();
        for(int i = 0; i < 150; i++){
            ball.insert(strings[i%20]);
        }
        for(int i = 0; i < 20; i ++) {
            assertTrue(ball.count(strings[i]) == 5);
        }
        ball = new Yarn();
        for(int i = 0; i < 100; i ++){
            assertTrue(ball.insert(strings[0]));
        }
        assertTrue(ball.count(strings[0]) == 100);
        for(int i = 0; i < 50; i ++){
            ball.remove(strings[0]);
        }
        assertTrue(ball.count(strings[0]) == 50);
        ball.removeAll(strings[0]);
        assertTrue(ball.count(strings[0]) == 0);
    }

    @Test
    public void testContains() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        assertTrue(ball.contains("dup"));
        assertTrue(ball.contains("unique"));
        assertFalse(ball.contains("forneymon"));

        // ## MY CODE ##
        ball = new Yarn();
        for(int i = 0; i < 10; i ++) {
            ball.insert(strings[i]);
        }
        for(int i = 0; i < 10; i ++) {
            assertTrue(ball.contains(strings[i]));
        }
        ball = new Yarn();
        for(int i = 0; i < 150; i++){
            ball.insert(strings[i%20]);
        }
        for(int i = 0; i < 20; i ++) {
            assertTrue(ball.contains(strings[i]));
        }
        ball = new Yarn();
        for(int i = 0; i < 100; i ++){
            assertTrue(ball.insert(strings[0]));
        }
        assertTrue(ball.contains(strings[0]));
        ball = new Yarn();
        for(int i = 0; i < 20; i ++){
            assertFalse(ball.contains(strings[i]));
        }
    }

    @Test
    public void testGetNth() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        Yarn comparison = ball.clone();
        for (int i = 0; i < ball.getSize(); i++) {
            String gotten = ball.getNth(i);
            assertTrue(comparison.contains(gotten));
            comparison.remove(gotten);
        }

        // ## MY CODE ##
        ball = new Yarn();
        for(int i = 0; i < 100; i ++){
            ball.insert(strings[0]);
        }
        for(int i = 0; i < 100; i ++){
            assertTrue(ball.getNth(i).equals(strings[0]));
        }
        ball = new Yarn();
        for(int i = 0; i < 100; i ++) {
            ball.insert(strings[i % 20]);
        }
        for(int i = 0; i < 20; i ++) {
            int count = 0;
            for (int j = 0; j < 100; j++) {
                count += ball.getNth(j) == strings[i] ? 1 : 0;
            }
            assertTrue(count == 5);
        }
    }

    @Test
    public void testGetMostCommon() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        ball.insert("cool");
        assertEquals(ball.getMostCommon(), "dup");
        ball.insert("cool");
        String mc = ball.getMostCommon();
        assertTrue(mc.equals("dup") || mc.equals("cool"));

        // ## MY CODE ##
        ball = new Yarn();
        for(int i = 0; i < 100; i ++){
            ball.insert(strings[0]);
        }
        assertTrue(ball.getMostCommon().equals(strings[0]));
        ball = new Yarn();
        for(int i = 0; i < 100; i ++) {
            ball.insert(strings[i % 20]);
        }

        ball = new Yarn();
        for(int i = 0; i < 90; i ++) {
            ball.insert(strings[i % 10]);
        }
        for(int i = 0; i < 10; i ++) {
            ball.insert(strings[(i % 10) + 10]);
        }

        boolean isMostCommon = false;
        for(int i = 0; i < 10; i ++) {
            isMostCommon = isMostCommon || ball.getMostCommon().equals(strings[i]);
        }
        assertTrue(isMostCommon);

        isMostCommon = false;
        for(int i = 10; i < 20; i ++) {
            isMostCommon = isMostCommon || ball.getMostCommon().equals(strings[i]);
        }
        assertFalse(isMostCommon);

    }

    @Test
    public void testClone() {
        Yarn ball = new Yarn();
        ball.insert("dup");
        ball.insert("dup");
        ball.insert("unique");
        Yarn dolly = ball.clone();
        assertEquals(dolly.count("dup"), 2);
        assertEquals(dolly.count("unique"), 1);
        dolly.insert("cool");
        assertFalse(ball.contains("cool"));

        // ## MY CODE ##
        ball = new Yarn();
        for(int i = 0; i < 100; i ++){
            ball.insert(strings[0]);
        }
        Yarn ball2 = ball.clone();
        assertEquals(ball2.count(strings[0]), ball.count(strings[0]));
        assertEquals(ball2.getMostCommon(), ball.getMostCommon());
        assertEquals(ball2.getSize(), ball.getSize());
        assertEquals(ball2.getUniqueSize(), ball.getUniqueSize());

        ball = new Yarn();
        for(int i = 0; i < 150; i++){
            ball.insert(strings[(int) (Math.random()*strings.length)]);
        }
        ball2 = ball.clone();
        for(String string : strings){
            assertEquals(ball2.count(string), ball.count(string));
        }
        assertEquals(ball2.getSize(), ball.getSize());
        assertEquals(ball2.getUniqueSize(), ball.getUniqueSize());

    }

    @Test
    public void testSwap() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("yo");
        y2.insert("sup");
        y1.swap(y2);
        assertTrue(y1.contains("yo"));
        assertTrue(y1.contains("sup"));
        assertTrue(y2.contains("dup"));
        assertTrue(y2.contains("unique"));
        assertFalse(y1.contains("dup"));

        // ## MY CODE ##
        Yarn ball = new Yarn();
        Yarn ball2 = new Yarn();
        for(int i = 0; i < 100; i ++){
            ball.insert(strings[(int) (Math.random()*strings.length)]);
        }
        for(int i = 0; i < 100; i ++){
            ball2.insert(strings[(int) (Math.random()*strings.length)]);
        }
        Yarn ballOld = ball.clone();
        Yarn ball2Old = ball2.clone();
        ball.swap(ball2);

        assertEquals(ball2.count(strings[0]), ballOld.count(strings[0]));
        assertEquals(ball2.getMostCommon(), ballOld.getMostCommon());
        assertEquals(ball2.getSize(), ballOld.getSize());
        assertEquals(ball2.getUniqueSize(), ballOld.getUniqueSize());

        assertEquals(ball.count(strings[0]), ball2Old.count(strings[0]));
        assertEquals(ball.getMostCommon(), ball2Old.getMostCommon());
        assertEquals(ball.getSize(), ball2Old.getSize());
        assertEquals(ball.getUniqueSize(), ball2Old.getUniqueSize());

        if(!ballOld.getMostCommon().equals(ball2Old.getMostCommon())){
            assertNotEquals(ball2.getMostCommon(), ball.getMostCommon());
        }
    }

    @Test
    public void testKnit() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("dup");
        y2.insert("cool");
        Yarn y3 = Yarn.knit(y1, y2);
        assertEquals(y3.count("dup"), 3);
        assertEquals(y3.count("unique"), 1);
        assertEquals(y3.count("cool"), 1);
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));

        // ## MY CODE ##
        Yarn ball = new Yarn();
        Yarn ball2 = new Yarn();
        for(int i = 0; i < 40; i ++){
            ball.insert(strings[(int) (Math.random()*strings.length)]);
        }
        for(int i = 0; i < 40; i ++){
            ball2.insert(strings[(int) (Math.random()*strings.length)]);
        }
        Yarn knitted = Yarn.knit(ball, ball2);
        assertEquals(knitted.getSize(), ball.getSize() + ball2.getSize());
        for(String string: strings){
            assertEquals(knitted.count(string), ball.count(string) + ball2.count(string));
        }

    }

    @Test
    public void testTear() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("dup");
        y2.insert("cool");
        Yarn y3 = Yarn.tear(y1, y2);
        assertEquals(y3.count("dup"), 1);
        assertEquals(y3.count("unique"), 1);
        assertFalse(y3.contains("cool"));
        y3.insert("test");
        assertFalse(y1.contains("test"));
        assertFalse(y2.contains("test"));

        // ## MY CODE ##
        Yarn ball = new Yarn();
        Yarn ball2 = new Yarn();
        for(int i = 0; i < 100; i ++){
            ball.insert(strings[(int) (Math.random()*strings.length)]);
        }
        for(int i = 0; i < 100; i ++){
            ball2.insert(strings[(int) (Math.random()*strings.length)]);
        }
        Yarn teared = Yarn.tear(ball, ball2);
        for(String string: strings) {
            assertEquals(teared.count(string), ball.count(string) - ball2.count(string) > 0 ? ball.count(string) - ball2.count(string) : 0);
        }
    }

    @Test
    public void testSameYarn() {
        Yarn y1 = new Yarn();
        y1.insert("dup");
        y1.insert("dup");
        y1.insert("unique");
        Yarn y2 = new Yarn();
        y2.insert("unique");
        y2.insert("dup");
        y2.insert("dup");
        assertTrue(Yarn.sameYarn(y1, y2));
        assertTrue(Yarn.sameYarn(y2, y1));
        y2.insert("test");
        assertFalse(Yarn.sameYarn(y1, y2));

        // ## MY CODE ##
        Yarn ball = new Yarn();
        Yarn ball2 = new Yarn();
        for(int i = 0; i < 40; i ++){
            ball.insert(strings[(int) (Math.random()*strings.length)]);
        }
        for(int i = 0; i < 40; i ++){
            ball2.insert(strings[(int) (Math.random()*strings.length)]);
        }
        Yarn knitted = Yarn.knit(ball, ball2);
        Yarn knitted2 = Yarn.knit(ball2, ball);
        assertFalse(Yarn.sameYarn(ball, ball2));
        assertFalse(Yarn.sameYarn(ball2, ball));
        assertTrue(Yarn.sameYarn(knitted, knitted2));
        for(int i = 0; i < 10; i ++){
            String toAdd = strings[(int) (Math.random()*strings.length)];
            knitted.insert(toAdd);
            knitted2.insert(toAdd);
        }
        assertTrue(Yarn.sameYarn(knitted, knitted2));
        for(int i = 0; i < 5; i ++){
            String toAdd = strings[(int) (Math.random()*strings.length)];
            knitted.removeAll(toAdd);
            knitted2.removeAll(toAdd);
        }
        assertTrue(Yarn.sameYarn(knitted, knitted2));
        for(int i = 0; i < 10; i ++){
            knitted.remove(strings[(int) (Math.random()*strings.length)]);
        }
        for(int i = 0; i < 10; i ++){
            knitted2.remove(strings[(int) (Math.random()*strings.length)]);
        }
        assertFalse(Yarn.sameYarn(knitted, knitted2));
    }

}
