package agh.cs.lab.constitution.textProcessors;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class EverythingBeforeFirstChapterRemoverTest {
    private EverythingBeforeFirstChapterRemover remover;
    @Before
    public void setUp() throws Exception {
        remover = new EverythingBeforeFirstChapterRemover();
    }

    @Test
    public void process_TextBeforeChapter_TextRemoved() throws Exception {

        final String inputText = "to be removed\n" +
                "Rozdział I\n" +
                "not to be removed.";
        final String expectedOutput = "Rozdział I\n" +
                "not to be removed.";
        String actualOutput = remover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    public void process_TextWithTwoChapter_TextBeforeFirstRemoved() throws Exception {

        final String inputText = "to be removed\n" +
                "Rozdział I\n" +
                "not to be removed.\n" +
                "Rozdział I\n" +
                "also not removed";
        final String expectedOutput = "Rozdział I\n" +
                "not to be removed.\n" +
                "Rozdział I\n" +
                "also not removed";
        String actualOutput = remover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_NoTextBeforeChapter_NothingRemoved() throws Exception {

        final String inputText = "Rozdział I\n" +
                "not to be removed.\n";
        final String expectedOutput = "Rozdział I\n" +
                "not to be removed.\n";
        String actualOutput = remover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_NoTextBeforeTwoChapters_NothingRemoved() throws Exception {

        final String inputText = "Rozdział I\n" +
                "not to be removed.\n" +
                "Rozdział I\n" +
                "also not removed";
        final String expectedOutput = "Rozdział I\n" +
                "not to be removed.\n" +
                "Rozdział I\n" +
                "also not removed";
        String actualOutput = remover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_EmptyString_NothingRemoved() throws Exception {

        final String inputText = "";
        final String expectedOutput = "";
        String actualOutput = remover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void process_TextWithoutChapters_NothingRemoved() throws Exception {

        final String inputText = "eee I\n" +
                "not to be removed.\n" +
                "eee I\n" +
                "also not removed";
        final String expectedOutput = "eee I\n" +
                "not to be removed.\n" +
                "eee I\n" +
                "also not removed";
        String actualOutput = remover.process(inputText);

        assertEquals(expectedOutput, actualOutput);
    }
}