package test.java.ohtu;

import static org.junit.Assert.*;
import org.junit.Test;
import main.java.ohtu.Multiplier;

public class MultiplierTest {

    @Test
    public void kertominenToimii() {
        Multiplier viisi = new Multiplier(5);

        assertEquals(5, viisi.multipliedBy(1));
        assertEquals(35, viisi.multipliedBy(7));
    }
}