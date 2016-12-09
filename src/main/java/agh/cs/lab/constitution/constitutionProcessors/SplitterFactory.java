package agh.cs.lab.constitution.constitutionProcessors;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class SplitterFactory
        implements ISplitterFactory {
    @Override
    public IConstitutionSplitter getConstitutionSplitter(String constitutionText) {
        return new ConstitutionSplitter(constitutionText);
    }

    @Override
    public IArticleSplitter getArticleSplitter(String articleText) {
        return new ArticleSplitter(articleText);
    }
    @Override
    public IChapterSplitter getChapterSplitter(String chapterText) {
        return new ChapterSplitter(chapterText);
    }
}
