package agh.cs.lab.constitution.textProcessors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Paweł Grochola on 06.12.2016.
 */
public class DateLineRemoverTest {

    private DateLineRemover dateLineRemover;

    @Before
    public void setUp() throws Exception {
        dateLineRemover = new DateLineRemover();
    }

    @Test
    public void process_TextWithDate_RemovedLineWithDate() throws Exception {
        final String inputText = "aaaaaa\n" +
                "rogienrger mw72 3232 \n" +
                "2016-12-04 \n" +
                "wgrgerg";

        final String expectedOutput = "aaaaaa\n" +
                "rogienrger mw72 3232 \n" +
                "wgrgerg";

        String actualOutput = dateLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_TextStringWithoutAnyDate_NothingRemoved() throws Exception {
        final String inputText = "aaaaaa\n" +
                "rogienrger mw72 3232 \n" +
                "wgrgerg";

        final String expectedOutput = "aaaaaa\n" +
                "rogienrger mw72 3232 \n" +
                "wgrgerg";

        String actualOutput = dateLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_TextWithMultipleDates_AllDatesRemoved() throws Exception {
        final String inputText = "aaaaaa\n" +
                "rogienrger mw72 3232 \n" +
                "2016-12-04 \n" +
                "wgrgerg\n" +
                "2016-12-34\n" +
                "2011-12-04 \n" +
                "eererer";

        final String expectedOutput = "aaaaaa\n" +
                "rogienrger mw72 3232 \n" +
                "wgrgerg\n" +
                "eererer";

        String actualOutput = dateLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_TextWithDateAtTheEnd_DateRemoved() throws Exception {
        final String inputText = "aaaaaa\n" +
                "rogienrger mw72 3232 \n" +
                "2016-12-04";

        final String expectedOutput = "aaaaaa\n" +
                "rogienrger mw72 3232 ";

        String actualOutput = dateLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_TextWithDateBetweenText_NothingRemoved() throws Exception {
        final String inputText = "aaaaaa\n" +
                "rogienrger mw72 2016-12-05 3232";

        final String expectedOutput = "aaaaaa\n" +
                "rogienrger mw72 2016-12-05 3232";

        String actualOutput = dateLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_EmptyString_NothingRemoved() throws Exception {
        final String inputText = "";

        final String expectedOutput = "";

        String actualOutput = dateLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }
}