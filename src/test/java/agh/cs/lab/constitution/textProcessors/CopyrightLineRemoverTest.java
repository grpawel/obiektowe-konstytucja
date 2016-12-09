package agh.cs.lab.constitution.textProcessors;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Paweł Grochola on 06.12.2016.
 */
public class CopyrightLineRemoverTest {
    private CopyrightLineRemover copyrightLineRemover;

    @Before
    public void setUp() throws Exception {
        copyrightLineRemover = new CopyrightLineRemover();
    }

    @Test
    public void process_TextWithCopyrightLine_RemovesCopyrightLine() throws Exception {
        final String inputText = "aaaaaa\n" +
                "©rogienrger mw72 3232 \n" +
                "wgrgerg";

        final String expectedOutput = "aaaaaa\n" +
                "wgrgerg";

        String actualOutput = copyrightLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_TextWithCopyrightLineAtEnd_RemovesCopyrightLine() throws Exception {
        final String inputText = "aaaaaa\n" +
                "rogienrger mw72 3232 \n" +
                "©wgrgerg";

        final String expectedOutput = "aaaaaa\n" +
                "rogienrger mw72 3232 ";

        String actualOutput = copyrightLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_TextWithoutCopyrightSign_NothingRemoved() throws Exception {
        final String inputText = "aaaaaa\n" +
                "rogienrger mw72 3232 \n" +
                "wgrgerg";

        final String expectedOutput = "aaaaaa\n" +
                "rogienrger mw72 3232 \n" +
                "wgrgerg";

        String actualOutput = copyrightLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_EmptyString_NothingRemoved() throws Exception {
        final String inputText = "";

        final String expectedOutput = "";

        String actualOutput = copyrightLineRemover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }
}