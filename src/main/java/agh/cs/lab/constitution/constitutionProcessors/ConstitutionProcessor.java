package agh.cs.lab.constitution.constitutionProcessors;

import agh.cs.lab.constitution.*;
import agh.cs.lab.constitution.textProcessors.ITextProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paweł Grochola on 06.12.2016.
 */
public class ConstitutionProcessor
        implements IConstitutionProcessor {
    private final List<ITextProcessor> processorsBeforeSplit = new ArrayList<>();
    private final List<ITextProcessor> processorsAfterSplit = new ArrayList<>();
    private final ISplitterFactory splitterFactory;

    public ConstitutionProcessor(ISplitterFactory splitterFactory) {
        this.splitterFactory = splitterFactory;
    }

    @Override
    public void addTextProcessor(ITextProcessor textProcessor){
        if(textProcessor.isApplicableForWholeText()) {
            processorsBeforeSplit.add(textProcessor);
        }
        else {
            processorsAfterSplit.add(textProcessor);
        }
    }

    @Override
    public IConstitution processText(String constitutionText) {
        String processedConstitutionText = constitutionText;
        for (ITextProcessor textProcessor : processorsBeforeSplit) {
            processedConstitutionText = textProcessor.process(processedConstitutionText);
        }
        IConstitutionSplitter constitutionSplitter = splitterFactory.getConstitutionSplitter(processedConstitutionText);
        constitutionSplitter.split();
        List<String> chapterTexts = constitutionSplitter.getChapterTexts();
        List<IChapter> chapters = new ArrayList<>();

        for (String chapterText : chapterTexts) {
            IChapterSplitter chapterSplitter = splitterFactory.getChapterSplitter(chapterText);
            chapterSplitter.split();
            List<String> articleTexts = chapterSplitter.getArticleTexts();
            List<IArticle> articles = new ArrayList<>();
            for (String articleText : articleTexts) {
                IArticleSplitter articleSplitter = splitterFactory.getArticleSplitter(articleText);
                articleSplitter.split();
                String articleContent = articleSplitter.getArticleContent();
                articleContent = applyProcessorsAfterSplit(articleContent);
                Integer articleNumber = articleSplitter.getArticleNumber();
                IArticle article = new Article(articleContent, articleNumber);
                articles.add(article);
            }
            Integer chapterNumber = chapterSplitter.getChapterNumber();
            String chapterHeading = chapterSplitter.getFirstLine();
            String chapterTitle = "";
            IChapter chapter = new Chapter(chapterNumber, chapterHeading, chapterTitle, articles);
            chapters.add(chapter);
        }
        return new Constitution(chapters);
    }

    private String applyProcessorsAfterSplit(String s) {
        for (ITextProcessor textProcessor : processorsAfterSplit) {
            s = textProcessor.process(s);
        }
        return s;
    }

}
