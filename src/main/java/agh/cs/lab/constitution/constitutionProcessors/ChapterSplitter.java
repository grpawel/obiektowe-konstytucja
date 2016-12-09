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
    private final RomanNumeralConverter romanNumeralConverter;

    public ChapterSplitter(String chapterText, RomanNumeralConverter numeralConverter) {
        this.chapterText = chapterText;
        this.romanNumeralConverter = numeralConverter;
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
        String romanNumber;
        try {
            romanNumber = chapterHeading.split(" ")[1];
        } catch(IndexOutOfBoundsException ex) {
            romanNumber = "I";
        }

        return romanNumeralConverter.convertToDecimal(romanNumber);
    }

    private Pair<String, String> extractFirstLine(String text) {
        String[] split = text.split("\\R", 2);
        String firstLine = split.length >= 1 ? split[0] : "";
        String restOfLines = split.length >= 2 ? split[1] : "";
        return new Pair<>(firstLine, restOfLines);
    }

    private List<String> splitIntoArticles(String contents) {
        final String[] articles = contents.split("(?=Art\\. \\d+\\.\\R)");
        return Collections.unmodifiableList(Arrays.asList(articles));
    }

}
