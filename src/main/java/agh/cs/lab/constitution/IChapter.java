package agh.cs.lab.constitution;

import java.util.List;

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
     * Returns list with all contained articles.
     */
    List<IArticle> getArticles();

    /**
     * Returns single article by its number.
     * @param articleNo Number of article within chapter to be returned.
     */
    IArticle getArticle(Integer articleNo);

}
