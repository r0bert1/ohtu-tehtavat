package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
  Pankki pankki;
  Viitegeneraattori viite;
  Varasto varasto;

  @Before
  public void setUp() {
    pankki = mock(Pankki.class);
    viite = mock(Viitegeneraattori.class);
    varasto = mock(Varasto.class);
  }

  @Test
  public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(1)).thenReturn(10); 
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

    // sitten testattava kauppa 
    Kauppa k = new Kauppa(varasto, pankki, viite);              

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
  }
}