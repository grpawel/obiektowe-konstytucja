package agh.cs.lab.constitution;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.omg.PortableInterceptor.Interceptor;

import static org.junit.Assert.*;

/**
 * Created by Paweł Grochola on 06.12.2016.
 */
public class RomanNumeralConverterTest {
    private RomanNumeralConverter converter;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
    public void convertToDecimal_IllegalCharacter_ExceptionThrown() throws Exception {
        thrown.expect(NumberFormatException.class);
        thrown.expectMessage("Invalid character 't' found when parsing roman numeral \"XXt\"");
        converter.convertToDecimal("XXt");
    }

    @Test
    public void convertToDecimal_NumbersWithLowerAndUpperCase_LetterCaseDoesntMatter() throws Exception {
        final Integer lowerCaseValue = converter.convertToDecimal("mdclxvi");
        final Integer upperCaseValue = converter.convertToDecimal("MDCLXVI");
        assertEquals(upperCaseValue, lowerCaseValue);
    }

    @Test
    public void convertToDecimal_EmptyString_0() throws Exception {
        final Integer actualValue = converter.convertToDecimal("");
        final Integer expectedValue = 0;

        assertEquals(expectedValue, actualValue);
    }
}