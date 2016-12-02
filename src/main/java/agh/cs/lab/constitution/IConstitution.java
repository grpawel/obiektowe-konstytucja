package agh.cs.lab.constitution;

import java.util.List;

/**
 * Created by Paweł Grochola on 02.12.2016.
 *
 */
public interface IConstitution {
    /**
     * Returns contents of multiple articles with specified numbers.
     * @param articleNumbers List of article numbers.
     */
    String getArticles(List<Integer> articleNumbers);

    /**
     * Returns single chapter containing articles.
     * @param number Number of chapter.
     */
    String getChapter(Integer number);
}
