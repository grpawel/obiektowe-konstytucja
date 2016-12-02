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
     * Returns single chapter containing articles.
     * @param number Number of chapter.
     */
    IChapter getChapter(Integer number);
}
