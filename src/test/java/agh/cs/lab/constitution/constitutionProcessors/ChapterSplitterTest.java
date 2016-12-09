package agh.cs.lab.constitution.constitutionProcessors;

import agh.cs.lab.constitution.RomanNumeralConverter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class ChapterSplitterTest {

    @Test
    public void split_TextWithArticles_ArticlesCorrectlyDivided() throws Exception {
        final String text = "Rozdział I\n" +
                "Art. 1.\n" +
                "Rzeczpospolita Polska1.\n" +
                "Art. 2.\n" +
                "Rzeczpospolita Polska2.\n" +
                "Art. 3.\n" +
                "Rzeczpospolita Polska3.\n";
        RomanNumeralConverter numeralConverter=mock(RomanNumeralConverter.class);
        when(numeralConverter.convertToDecimal("I")).thenReturn(1);
        ChapterSplitter chapterSplitter = new ChapterSplitter(text, numeralConverter);

        chapterSplitter.split();

        List<String> expectedArticles = Arrays.asList("Art. 1.\nRzeczpospolita Polska1.\n",
                "Art. 2.\nRzeczpospolita Polska2.\n",
                "Art. 3.\nRzeczpospolita Polska3.\n");

        List<String> actualArticles = chapterSplitter.getArticleTexts();

        assertEquals(expectedArticles, actualArticles);
        assertEquals(new Integer(1), chapterSplitter.getChapterNumber());
        assertEquals("Rozdział I", chapterSplitter.getFirstLine());
    }

    @Test
    public void split_TextWithoutArticles_ArticlesListIsEmpty() throws Exception {
        final String text = "Rozdział II";

        RomanNumeralConverter numeralConverter=mock(RomanNumeralConverter.class);
        when(numeralConverter.convertToDecimal("II")).thenReturn(2);
        ChapterSplitter chapterSplitter = new ChapterSplitter(text, numeralConverter);

        chapterSplitter.split();

        List<String> expectedArticles = new ArrayList<>();
        expectedArticles.add("");
        List<String> actualArticles = chapterSplitter.getArticleTexts();

        assertArrayEquals(expectedArticles.toArray(), actualArticles.toArray());
        assertEquals(new Integer(2), chapterSplitter.getChapterNumber());
        assertEquals("Rozdział II", chapterSplitter.getFirstLine());
    }
}