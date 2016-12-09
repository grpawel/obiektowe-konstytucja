package agh.cs.lab.constitution;


import java.util.List;
import java.util.Optional;

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
    Optional<IChapter> getChapter(Integer chapterNo);

    /**
     * Returns single article.
     */
    Optional<IArticle> getArticle(Integer articleNo);

    /**
     * Returns list of articles with numbers in list. Ignores non-existent article numbers.
     */
    List<IArticle> getArticles(List<Integer> articleNos);
}
