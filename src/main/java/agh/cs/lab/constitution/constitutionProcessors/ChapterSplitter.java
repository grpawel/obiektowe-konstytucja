package agh.cs.lab.constitution.constitutionProcessors;

import agh.cs.lab.constitution.RomanNumeralConverter;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class ChapterSplitter
        implements IChapterSplitter {
    private final String chapterText;
    private String firstLine;
    private Integer chapterNumber;
    private List<String> articleTexts;

    public ChapterSplitter(String chapterText) {
        this.chapterText = chapterText;
    }

    @Override
    public void split() {
        Pair<String, String> dividedChapter = extractFirstLine(chapterText);
        this.firstLine = dividedChapter.getKey();
        String chapterContent = dividedChapter.getValue();
        this.articleTexts = splitIntoArticles(chapterContent);
        this.chapterNumber = extractChapterNumber(this.firstLine);
    }

    @Override
    public String getFirstLine() {
        return firstLine;
    }

    @Override
    public Integer getChapterNumber() {
        return chapterNumber;
    }

    @Override
    public List<String> getArticleTexts() {
        return articleTexts;
    }

    private Integer extractChapterNumber(String chapterHeading) {
        RomanNumeralConverter romanConverter = new RomanNumeralConverter();
        String romanNumber = chapterHeading.split(" ")[1];
        return romanConverter.convertToDecimal(romanNumber);
    }

    private Pair<String, String> extractFirstLine(String text) {
        String[] split = text.split("\\R", 2);
        return new Pair<>(split[0], split[1]);
    }

    private List<String> splitIntoArticles(String contents) {
        final String[] articles = contents.split("(?=Art\\. \\d\\.\\R)");
        return Collections.unmodifiableList(Arrays.asList(articles));
    }

}
