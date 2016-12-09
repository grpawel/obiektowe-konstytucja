package agh.cs.lab.constitution;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by Paweł Grochola on 02.12.2016.
 */
public interface IChapter {
    /**
     * Returns chapter number.
     */
    Integer getChapterNo();

    /**
     * Returns heading of chapter, eg. "Rozdział II"
     */
    String getHeading();

    /**
     * Returns title of chapter.
     */
    String getTitle();

    /**
     * Returns contents of chapter. Includes all articles.
     */
    String getContents();

    /**
     * Returns list with all contained articles.
     */
    List<IArticle> getArticles();

    /**
     * Returns single article by its number.
     *
     * @param articleNo Number of article within chapter to be returned.
     */
    Optional<IArticle> getArticle(Integer articleNo);

    /**
     * Returns minimum article number contained within chapter.
     */
    Integer getMinArticleNo();

    /**
     * Returns maximum article number contained within chapter.
     */
    Integer getMaxArticleNo();

}
