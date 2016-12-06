package agh.cs.lab.constitution;

import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.Interceptor;

import static org.junit.Assert.*;

/**
 * Created by Paweł Grochola on 06.12.2016.
 */
public class RomanNumeralConverterTest {
    private RomanNumeralConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new RomanNumeralConverter();
    }

    @Test
    public void convertToDecimal_X_10() throws Exception {
        final Integer actualValue = converter.convertToDecimal("X");
        final Integer expectedValue = 10;

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void convertToDecimal_III_3() throws Exception {
        final Integer actualValue = converter.convertToDecimal("III");
        final Integer expectedValue = 3;

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void convertToDecimal_IC_99() throws Exception {
        final Integer actualValue = converter.convertToDecimal("IC");
        final Integer expectedValue = 99;

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void convertToDecimal_IIM_998() throws Exception {
        final Integer actualValue = converter.convertToDecimal("IIM");
        final Integer expectedValue = 998;

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void convertToDecimal_XLIV_44() throws Exception {
        final Integer actualValue = converter.convertToDecimal("XLIV");
        final Integer expectedValue = 44;

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void convertToDecimal_MDCLXVI_1666() throws Exception {
        final Integer actualValue = converter.convertToDecimal("MDCLXVI");
        final Integer expectedValue = 1666;

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void convertToDecimal_IllegalCharacter_Ignored() throws Exception {
        final Integer actualValue = converter.convertToDecimal("XXt");
        final Integer expectedValue = 20;

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void convertToDecimal_EmptyString_0() throws Exception {
        final Integer actualValue = converter.convertToDecimal("");
        final Integer expectedValue = 0;

        assertEquals(expectedValue, actualValue);
    }
}