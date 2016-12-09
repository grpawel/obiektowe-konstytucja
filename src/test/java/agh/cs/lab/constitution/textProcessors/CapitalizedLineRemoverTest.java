package agh.cs.lab.constitution.textProcessors;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class CapitalizedLineRemoverTest {
    private CapitalizedLineRemover capitalizedLineRemover;

    @Before
    public void setUp() throws Exception {
        capitalizedLineRemover = new CapitalizedLineRemover();
    }

    @Test
    public void process_TextWithCapitalizedLine_CapitalizedLineRemoved() throws Exception {
        final String inputText = "aaaaaa\n" +
                "TEST\n" +
                "wgrgerg";

        final String expectedOutput = "aaaaaa\n" +
                "wgrgerg";
        String actualOutput = capitalizedLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_TextWithDiacritics_CapitalizedLineRemoved() throws Exception {
        final String inputText = "aaaaaa\n" +
                "TESTĄĘĆŚŹŃŻÓŁ\n" +
                "wgrgerg";

        final String expectedOutput = "aaaaaa\n" +
                "wgrgerg";
        String actualOutput = capitalizedLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_TextWithCapitalizedLineAtTheEnd_CapitalizedLineRemoved() throws Exception {
        final String inputText = "aaaaaa\n" +
                "TESTLINE\n" +
                "wgrgerg";

        final String expectedOutput = "aaaaaa\n" +
                "wgrgerg";
        String actualOutput = capitalizedLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_TextWithCapitalizedLineAtTheBeginning_CapitalizedLineRemoved() throws Exception {
        final String inputText = "TESTLINE\n" +
                "aaaaaa\n" +
                "Wgrgerg";

        final String expectedOutput = "aaaaaa\n" +
                "Wgrgerg";
        String actualOutput = capitalizedLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_CapitalizedLineWithSpacesAndInterpunction_CapitalizedLineRemoved() throws Exception {

        final String inputText = "aaaaaa\n" +
                "TEST LINE, TO BE REMOVED.\n" +
                "wgrgerg";
        final String expectedOutput = "aaaaaa\n" +
                "wgrgerg";
        String actualOutput = capitalizedLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_LineEndingWithDot_NothingRemoved() throws Exception {

        final String inputText = "aaaaaa.\n" +
                "wgrgerg";
        final String expectedOutput = "aaaaaa.\n" +
                "wgrgerg";
        String actualOutput = capitalizedLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }
}