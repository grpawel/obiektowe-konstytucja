package agh.cs.lab.constitution;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class ConstitutionTest {

    private Constitution constitution;
    @Mock
    private Chapter chapter1;
    @Mock
    private Chapter chapter2;

    @Mock
    private Article article1;
    @Mock
    private Article article2;

    @Mock
    private Article article3;
    @Mock
    private Article article4;

    @Before
    public void setUp() throws Exception {
        article1 = mock(Article.class);
        when(article1.getArticleNo()).thenReturn(1);
        article2 = mock(Article.class);
        when(article2.getArticleNo()).thenReturn(2);
        article3 = mock(Article.class);
        when(article3.getArticleNo()).thenReturn(3);
        article4 = mock(Article.class);
        when(article4.getArticleNo()).thenReturn(4);
        chapter1 = mock(Chapter.class);
        when(chapter1.getChapterNo()).thenReturn(1);
        when(chapter1.getArticle(1)).thenReturn(Optional.of(article1));
        when(chapter1.getArticle(2)).thenReturn(Optional.of(article2));
        when(chapter1.getArticles()).thenReturn(Arrays.asList(article1, article2));
        when(chapter1.getMinArticleNo()).thenReturn(1);
        when(chapter1.getMaxArticleNo()).thenReturn(2);
        chapter2 = mock(Chapter.class);
        when(chapter2.getChapterNo()).thenReturn(2);
        when(chapter2.getArticle(3)).thenReturn(Optional.of(article3));
        when(chapter2.getArticle(4)).thenReturn(Optional.of(article4));
        when(chapter2.getArticles()).thenReturn(Arrays.asList(article3, article4));
        when(chapter2.getMinArticleNo()).thenReturn(3);
        when(chapter2.getMaxArticleNo()).thenReturn(4);

        constitution = new Constitution(Arrays.asList(chapter1, chapter2));
    }

    @Test
    public void getChapters__ReturnsAllChapters() throws Exception {
        List<IChapter> expectedChapters = Arrays.asList(chapter1, chapter2);
        assertEquals(expectedChapters, constitution.getChapters());
    }

    @Test
    public void getChapter_ExistingArticleNumber_ReturnsCorrectArticle() throws Exception {
        assertEquals(Optional.of(chapter1), constitution.getChapter(1));
        assertEquals(Optional.of(chapter2), constitution.getChapter(2));
    }

    @Test
    public void getChapter_TooSmallChapterNumber_ReturnsEmptyOptional() throws Exception {
        assertFalse(constitution.getChapter(0).isPresent());
    }

    @Test
    public void getChapter_TooBigChapterNumber_ReturnsEmptyOptional() throws Exception {
        assertFalse(constitution.getChapter(3).isPresent());
    }

    @Test
    public void getArticle_ExistingArticleNumber_ReturnsCorrectArticle() throws Exception {
        assertEquals(Optional.of(article1), constitution.getArticle(1));
        assertEquals(Optional.of(article2), constitution.getArticle(2));
        assertEquals(Optional.of(article3), constitution.getArticle(3));
        assertEquals(Optional.of(article4), constitution.getArticle(4));
    }

    @Test
    public void getArticle_TooSmallArticleNumber_ReturnsEmptyOptional() throws Exception {
        assertFalse(constitution.getArticle(0).isPresent());
    }

    @Test
    public void getArticle_TooBigArticleNumber_ReturnsEmptyOptional() throws Exception {
        assertFalse(constitution.getArticle(5).isPresent());
    }

    @Test
    public void getArticles_SingleCorrectArticleNumber_ReturnsCorrectArticle() throws Exception {
        List<Integer> articleNumbers = Arrays.asList(1);
        List<IArticle> expectedArticles = Arrays.asList(article1);
        List<IArticle> actualArticles = constitution.getArticles(articleNumbers);

        assertEquals(expectedArticles, actualArticles);
    }

    @Test
    public void getArticles_ArticleRangeSpanningAllArticles_ReturnsCorrectArticles() throws Exception {
        List<Integer> articleNumbers = Arrays.asList(1,2,3,4);
        List<IArticle> expectedArticles = Arrays.asList(article1, article2, article3, article4);
        List<IArticle> actualArticles = constitution.getArticles(articleNumbers);

        assertEquals(expectedArticles, actualArticles);
    }

    @Test
    public void getArticles_ArticleRangeIncludingInvalid_ReturnsCorrectArticlesAndIgnoresInvalid() throws Exception {
        List<Integer> articleNumbers = Arrays.asList(0,1,2,5);
        List<IArticle> expectedArticles = Arrays.asList(article1, article2);
        List<IArticle> actualArticles = constitution.getArticles(articleNumbers);

        assertEquals(expectedArticles, actualArticles);
    }

    @Test
    public void getArticles_ArticleNumbersNotInOrder_ReturnedArticlesHaveSameOrder() throws Exception {
        List<Integer> articleNumbers = Arrays.asList(4,2,3);
        List<IArticle> expectedArticles = Arrays.asList(article4, article2, article3);
        List<IArticle> actualArticles = constitution.getArticles(articleNumbers);

        assertEquals(expectedArticles, actualArticles);
    }
}