package agh.cs.lab.constitution.constitutionProcessors;

import agh.cs.lab.constitution.IConstitution;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public interface ISplitterFactory {
    IConstitutionSplitter getConstitutionSplitter(String constitutionText);
    IArticleSplitter getArticleSplitter(String articleText);
    IChapterSplitter getChapterSplitter(String chapterText);
}
