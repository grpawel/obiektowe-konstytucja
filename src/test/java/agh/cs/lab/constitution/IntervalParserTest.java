package agh.cs.lab.constitution;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class IntervalParserTest {
    private IntervalParser intervalParser;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        intervalParser = new IntervalParser();
    }

    @Test
    public void parse_NumbersSeparatedByCommas_ListWithCorrectNumbers() throws Exception {
        String numbers = "1,3,4,50";

        List<Integer> actualResult = intervalParser.parse(numbers);
        List<Integer> expectedResult = Arrays.asList(1, 3, 4, 50);

        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void parse_NumbersRanges_ListWithCorrectNumbers() throws Exception {
        String numbers = "1-3,10-15,55";

        List<Integer> actualResult = intervalParser.parse(numbers);
        List<Integer> expectedResult = Arrays.asList(1, 2, 3, 10, 11, 12, 13, 14, 15, 55);

        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void parse_EmptyString_EmptyList() throws Exception {
        String numbers = "";

        List<Integer> actualResult = intervalParser.parse(numbers);
        List<Integer> expectedResult = Collections.emptyList();

        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void parse_StringWithUnnecessaryCharacters_ThrowsException() throws Exception {
        String numbers = "1_,.3,4,aa50";

        exception.expect(IncorrectIntervalException.class);
        exception.expectMessage("Incorrect characters \"_.aa\" in expression \"1_,.3,4,aa50\".");
        intervalParser.parse(numbers);
    }

    @Test
    public void parse_RangeWithFirstNumberBigger_ThrowsException() throws Exception {
        String numbers = "3-1";

        exception.expect(IncorrectIntervalException.class);
        exception.expectMessage("First number cannot be bigger than second in expression \"3-1\"");
        intervalParser.parse(numbers);
    }

    @Test
    public void parse_RangeWithThreeNumbers_ThrowsException() throws Exception {
        String numbers = "1-2-3";

        exception.expect(IncorrectIntervalException.class);
        exception.expectMessage("Too many numbers in expression \"1-2-3\"");
        intervalParser.parse(numbers);
    }

    @Test
    public void parse_RangeWithoutLeftNumber_ThrowsException() throws Exception {
        String numbers = "-3";

        exception.expect(IncorrectIntervalException.class);
        exception.expectMessage("Expression \"-3\" should contain two numbers.");
        intervalParser.parse(numbers);
    }

    @Test
    public void parse_RangeWithoutRightNumber_ThrowsException() throws Exception {
        String numbers = "3-";

        exception.expect(IncorrectIntervalException.class);
        exception.expectMessage("Expression \"3-\" should contain two numbers.");
        intervalParser.parse(numbers);
    }

    @Test
    public void parse_NumbersSeparatedByCommasAndSpaces_ListWithCorrectNumbers() throws Exception {
        String numbers = "1,2 3, 4 , 5";

        List<Integer> actualResult = intervalParser.parse(numbers);
        List<Integer> expectedResult = Arrays.asList(1, 2, 3, 4, 5);

        assertThat(actualResult, is(expectedResult));
    }
}