import static org.junit.Assert.*;

/**
 * Created by juanj on 9/8/2016.
 */
public class FlippingForneymonCardTest {
    @org.junit.Test
    public void testToSring() throws Exception {

        FlippingForneymonCard burny = new FlippingForneymonCard("burny", "Burnymon", false);
        assertEquals(burny.toString(), "Burnymon: burny");
        burny.flip();
        assertEquals(burny.toString(), "?: ?");

        burny = new FlippingForneymonCard("burny", "Burnymon", true);
        assertEquals(burny.toString(), "?: ?");
        burny.flip();
        assertEquals(burny.toString(), "Burnymon: burny");

        FlippingForneymonCard missingNu = new FlippingForneymonCard();
        assertEquals(missingNu.toString(), "?: ?" );
        missingNu.flip();
        assertEquals(missingNu.toString(), "Burnymon: MissingNu");
    }

    @org.junit.Test
    public void testFlip() throws Exception {

        FlippingForneymonCard burny = new FlippingForneymonCard("burny", "Burnymon", false);
        assertEquals(burny.isFaceDown(), false);
        burny.flip();
        assertEquals(burny.isFaceDown(), true);
        burny.flip();
        assertEquals(burny.isFaceDown(), false);

        burny = new FlippingForneymonCard("burny", "Burnymon", true);
        assertEquals(burny.isFaceDown(), true);
        burny.flip();
        assertEquals(burny.isFaceDown(), false);
        burny.flip();
        assertEquals(burny.isFaceDown(), true);

        burny = new FlippingForneymonCard();
        assertEquals(burny.isFaceDown(), true);
        burny.flip();
        assertEquals(burny.isFaceDown(), false);
        burny.flip();
        assertEquals(burny.isFaceDown(), true);

    }

    @org.junit.Test
    public void testMatch() throws Exception {

        FlippingForneymonCard burny = new FlippingForneymonCard("burny", "Burnymon", false);
        FlippingForneymonCard burny2 = new FlippingForneymonCard("burny", "Burnymon", true);
        assertEquals(burny.match(burny2), 2);
        assertEquals(burny2.match(burny), 2);

        burny2.flip();
        assertEquals(burny.match(burny2), 1);
        assertEquals(burny2.match(burny), 1);

        burny.flip();
        assertEquals(burny.match(burny2), 2);
        assertEquals(burny2.match(burny), 2);

        FlippingForneymonCard missingNu = new FlippingForneymonCard();
        assertEquals(missingNu.match(burny), 2);
        assertEquals(burny.match(missingNu), 2);
        missingNu.flip();
        burny.flip();
        assertEquals(missingNu.match(burny), 0);
        assertEquals(burny.match(missingNu), 0);

        burny2 = new FlippingForneymonCard("burny2", "Burnymon", false);
        assertEquals(burny.match(burny2), 0);
        assertEquals(burny2.match(burny), 0);

        burny2 = new FlippingForneymonCard("burny", "Dampymon", false);
        assertEquals(burny.match(burny2), 0);
        assertEquals(burny2.match(burny), 0);

        burny2 = new FlippingForneymonCard("burny", "Leafymon", false);
        assertEquals(burny.match(burny2), 0);
        assertEquals(burny2.match(burny), 0);

    }

    @org.junit.Test
    public void testIsFaceDown() throws Exception {

        FlippingForneymonCard burny = new FlippingForneymonCard("burny", "Burnymon", false);
        assertEquals(burny.isFaceDown(), false);

        burny = new FlippingForneymonCard("burny", "Burnymon", true);
        assertEquals(burny.isFaceDown(), true);

        burny = new FlippingForneymonCard("burny", "Leafymon", false);
        assertEquals(burny.isFaceDown(), false);

        burny = new FlippingForneymonCard("burny2", "Leafymon", true);
        assertEquals(burny.isFaceDown(), true);

        burny = new FlippingForneymonCard();
        assertEquals(burny.isFaceDown(), true);

    }

}