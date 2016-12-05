package agh.cs.lab.constitution;


import java.util.List;

/**
 * Created by Paweł Grochola on 02.12.2016.
 *
 */
public interface IConstitution {
    /**
     * Returns list with all chapters.
     */
    List<IChapter> getChapters();

    /**
     * Returns single chapter containing articles. If no chapter with number exists, returns null.
     * @param chapterNo Number of chapter.
     */
    IChapter getChapter(Integer chapterNo);

    /**
     * Returns single article.
     */
    IArticle getArticle(Integer articleNo);

    /**
     * Returns list of articles with numbers in list. Ignores non-existent article numbers.
     */
    List<IArticle> getArticles(List<Integer> articleNos);
}
