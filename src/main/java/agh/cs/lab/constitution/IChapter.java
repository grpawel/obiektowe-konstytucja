package agh.cs.lab.constitution;

import java.util.Collection;

/**
 * Created by Paweł Grochola on 02.12.2016.
 */
public interface IChapter {
    /**
     * Returns chapter number.
     */
    Integer getChapterNo();

    /**
     * Returns contents of chapter. Includes all articles.
     */
    String getContents();

    /**
     * Returns title of chapter.
     */
    String getTitle();

    /**
     * Returns list with all contained articles.
     */
    Collection<IArticle> getArticles();

    /**
     * Returns single article by its number.
     * @param articleNo Number of article within chapter to be returned.
     */
    IArticle getArticle(Integer articleNo);

    /**
     Returns minimum article number contained within chapter.
     */
    Integer getMinArticleNo();

    /**
     * Returns maximum article number contained within chapter.
     */
    Integer getMaxArticleNo();

}
