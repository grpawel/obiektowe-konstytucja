package agh.cs.lab.constitution.constitutionProcessors;

import agh.cs.lab.constitution.IConstitution;

import java.util.List;

/**
 * Created by Paweł Grochola on 02.12.2016.
 */
public interface IConstitutionSplitter {
    /**
     * Splits string with constitution into structure of chapters and articles.
     * @return Structure with chapters and articles.
     */
    void split();

    List<String> getChapterTexts();
}
