package agh.cs.lab.constitution.constitutionProcessors;

import java.util.List;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public interface IChapterSplitter {
    void split();

    String getFirstLine();

    Integer getChapterNumber();

    List<String> getArticleTexts();
}
