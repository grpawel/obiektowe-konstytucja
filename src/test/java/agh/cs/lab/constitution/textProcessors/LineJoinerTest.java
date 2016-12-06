package agh.cs.lab.constitution.textProcessors;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Paweł Grochola on 06.12.2016.
 */
public class LineJoinerTest {
    private LineJoiner lineJoiner;

    @Before
    public void setUp() throws Exception {
        lineJoiner = new LineJoiner();
    }

    @Test
    public void process_MultilineText_TextInSingleLineWithSpacesAdded() throws Exception {
        final String inputText = "abcdef\n" +
                "ghijk lmnop qrst \n" +
                "uvwxyz";

        final String expectedOutput = "abcdef " +
                "ghijk lmnop qrst  " +
                "uvwxyz";

        String actualOutput = lineJoiner.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_TextWithLinesStartingWithNumbers_LinesWithNumbersAreNotJoined() throws Exception {
        final String inputText = "abcdef\n" +
                "ghijk lmnop qrst \n" +
                "uvwxyz";

        final String expectedOutput = "abcdef " +
                "ghijk lmnop qrst  " +
                "uvwxyz";

        String actualOutput = lineJoiner.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_TextWithHyphensAtTheEnd_LinesAreJoinedAndHyphensRemoved() throws Exception {
        final String inputText = "abcdef-\n" +
                "ghijk lmnop qrst \n" +
                "uvwxyz-\n" +
                "ABCDEF";

        final String expectedOutput = "abcdef" +
                "ghijk lmnop qrst  " +
                "uvwxyz" +
                "ABCDEF";

        String actualOutput = lineJoiner.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_TextWithLinesStartingWithNumbers_LinesAreJoinedUnlessStartingWithNumbers() throws Exception {
        final String inputText = "abcdef\n" +
                "1ghijk lmnop qrst \n" +
                "2uvwxyz\n" +
                "ABCDEF";

        final String expectedOutput = "abcdef\n" +
                "1ghijk lmnop qrst \n" +
                "2uvwxyz " +
                "ABCDEF";

        String actualOutput = lineJoiner.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_TextWithHyphenInside_HyphenIsNotRemoved() throws Exception {
        final String inputText = "abdjeef  - wefw-efwe";

        final String expectedOutput = "abdjeef  - wefw-efwe";

        String actualOutput = lineJoiner.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_OneLineText_NothingRemoved() throws Exception {
        final String inputText = "abdjeef wefwefwe";

        final String expectedOutput = "abdjeef wefwefwe";

        String actualOutput = lineJoiner.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_EmptyString_NothingRemoved() throws Exception {
        final String inputText = "";

        final String expectedOutput = "";

        String actualOutput = lineJoiner.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }
}