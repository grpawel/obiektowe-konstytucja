package agh.cs.lab.constitution.constitutionProcessors;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class ArticleSplitterTest {
    @Test
    public void split_CorrectArticle_CorrectlySplit() throws Exception {
        String article = "Art. 20.\n" +
                "Społeczna gospodarka rynkowa oparta na wolności działalności gospodarczej, wła-\n" +
                "sności prywatnej oraz solidarności, dialogu i współpracy partnerów społecznych\n" +
                "stanowi podstawę ustroju gospodarczego Rzeczypospolitej Polskiej.";

        IArticleSplitter articleSplitter = new ArticleSplitter(article);
        articleSplitter.split();

        Integer actualNumber = articleSplitter.getArticleNumber();
        String actualContent = articleSplitter.getArticleContent();
        Integer expectedNumber = 20;
        String expectedContent = "Społeczna gospodarka rynkowa oparta na wolności działalności gospodarczej, wła-\n" +
                "sności prywatnej oraz solidarności, dialogu i współpracy partnerów społecznych\n" +
                "stanowi podstawę ustroju gospodarczego Rzeczypospolitej Polskiej.";
        assertEquals(expectedNumber, actualNumber);
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void split_EmptyArticle_EmptyContentsMinusOneNumber() throws Exception {
        String article = "";
        IArticleSplitter articleSplitter = new ArticleSplitter(article);
        articleSplitter.split();

        Integer actualNumber = articleSplitter.getArticleNumber();
        String actualContent = articleSplitter.getArticleContent();
        Integer expectedNumber = -1;
        String expectedContent = "";
        assertEquals(expectedNumber, actualNumber);
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void split_HeadingWithNoNumber_EmptyContentsMinusOneNumber() throws Exception {
        String article = "Art. .\n" +
                "oineoirnoger";
        IArticleSplitter articleSplitter = new ArticleSplitter(article);
        articleSplitter.split();

        Integer actualNumber = articleSplitter.getArticleNumber();
        String actualContent = articleSplitter.getArticleContent();
        Integer expectedNumber = -1;
        String expectedContent = "oineoirnoger";
        assertEquals(expectedNumber, actualNumber);
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void split_OnlyHeading_EmptyContentsCorrectNumber() throws Exception {
        String article = "Art. 32.\n";
        IArticleSplitter articleSplitter = new ArticleSplitter(article);
        articleSplitter.split();

        Integer actualNumber = articleSplitter.getArticleNumber();
        String actualContent = articleSplitter.getArticleContent();
        Integer expectedNumber = 32;
        String expectedContent = "";
        assertEquals(expectedNumber, actualNumber);
        assertEquals(expectedContent, actualContent);

    }

}