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
  public void yhdenTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    when(viite.uusi()).thenReturn(42);

    when(varasto.saldo(1)).thenReturn(10); 
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

    Kauppa k = new Kauppa(varasto, pankki, viite);              

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   
  }

  @Test
  public void kahdenEriTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    when(viite.uusi()).thenReturn(42);

    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

    when(varasto.saldo(2)).thenReturn(10);
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 4));

    Kauppa k = new Kauppa(varasto, pankki, viite);

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(2);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(9));  
  }

  @Test
  public void kahdenSamanTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    when(viite.uusi()).thenReturn(42);

    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    
    Kauppa k = new Kauppa(varasto, pankki, viite);

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(10));
  }

  @Test
  public void yhdenSaatavanJaYhdenLoppuneenTuotteenOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
    when(viite.uusi()).thenReturn(42);

    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

    when(varasto.saldo(2)).thenReturn(0);
    
    Kauppa k = new Kauppa(varasto, pankki, viite);

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(2);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
  }

  @Test
  public void kaupanAlioitaAsiointiMetodiNollaaEdellisenOstoksenTiedot() {
    when(viite.uusi()).thenReturn(42);

    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    
    Kauppa k = new Kauppa(varasto, pankki, viite);

    k.aloitaAsiointi();
    k.lisaaKoriin(1);

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
  }

  @Test
  public void kauppaPyytaaUudenViitenumeronJokaiselleOstokselle() {
    when(viite.uusi())
      .thenReturn(42)
      .thenReturn(43);

    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    
    Kauppa k = new Kauppa(varasto, pankki, viite);

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");

    verify(pankki).tilisiirto(eq("pekka"), eq(43), eq("12345"), eq("33333-44455"), eq(5));
  }

  @Test
  public void tuotteenPoistaminenKoristaPalauttaaSenVarastoonEikaSiitaVeloiteta() {
    when(viite.uusi()).thenReturn(42);

    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    
    Kauppa k = new Kauppa(varasto, pankki, viite);

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.poistaKorista(1);
    k.tilimaksu("pekka", "12345");

    verify(varasto, times(2)).haeTuote(1);
    verify(varasto, times(1)).palautaVarastoon(new Tuote(1, "maito", 5));

    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
  }
}