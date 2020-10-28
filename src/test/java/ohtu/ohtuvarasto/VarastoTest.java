package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto, varasto2, varasto3, varasto4, varasto5;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(-10);
        varasto3 = new Varasto(10, 5);
        varasto4 = new Varasto(-10, -10);
        varasto5 = new Varasto(10, 15);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void luoEpakelpoVarasto() {
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
        assertEquals(0, varasto4.getSaldo(), vertailuTarkkuus);
        assertEquals(0, varasto4.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(10, varasto5.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);
        varasto2.lisaaVarastoon(20);
        varasto3.lisaaVarastoon(-8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(0, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
        assertEquals(5, varasto3.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);
        varasto3.otaVarastosta(-2);
        varasto5.otaVarastosta(20);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
        // varastossa 3 tilaa on alunperin 5 eikä negatiivinen vähennys muuta sitä
        assertEquals(5, varasto3.paljonkoMahtuu(), vertailuTarkkuus);
        // varastossa 5 ylisuuri poisto ei mahdollista saldon menemistä miinukselle
        assertEquals(10, varasto5.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void tulostusOikein() {
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto3.toString());
    }
}