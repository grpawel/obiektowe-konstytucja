package agh.cs.lab.constitution;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Paweł Grochola on 06.12.2016.
 */
public class ConstitutionSplitter
        implements IConstitutionSplitter {
    @Override
    public IConstitution split(String constitutionText) {
        List<String> chapterTexts = splitIntoChapters(constitutionText);
        List<IChapter> chapters = new ArrayList<>();

        for (String chapterText : chapterTexts) {
            Pair<String, String> chapterHeadingAndContents = extractFirstLine(chapterText);
            String chapterHeading = chapterHeadingAndContents.getKey();
            String chapterContents = chapterHeadingAndContents.getValue();
            Integer chapterNumber = extractChapterNumber(chapterHeading);

            List<String> articleTexts = splitIntoArticles(chapterContents);
            List<IArticle> articles = new ArrayList<>();
            for (String articleText : articleTexts) {
                Pair<String, String> articleHeadingAndContents = extractFirstLine(articleText);
                String articleHeading = articleHeadingAndContents.getKey();
                String articleContents = articleHeadingAndContents.getValue();
                Integer articleNumber = extractArticleNumber(articleHeading);

                IArticle article = new Article(articleContents, articleNumber);
                articles.add(article);
            }

            IChapter chapter = new Chapter(chapterNumber, chapterHeading, "", chapterContents, articles);
            chapters.add(chapter);
        }

        IConstitution constitution = new Constitution(chapters);
        return constitution;
    }

    private List<String> splitIntoChapters(String constitution) {
        String[] chapters = constitution.split("(?=Rozdział [IVXLCDM]+\\R)");
        return Arrays.asList(chapters);
    }

    private Pair<String, String> extractFirstLine(String text) {
        String[] split = text.split("\\R", 2);
        return new Pair<>(split[0], split[1]);
    }

    private Integer extractChapterNumber(String chapterHeading) {
        RomanNumeralConverter romanConverter = new RomanNumeralConverter();
        String romanNumber = chapterHeading.split(" ")[1];
        return romanConverter.convertToDecimal(romanNumber);
    }

    private List<String> splitIntoArticles(String chapter) {
        final String[] articles = chapter.split("(?=Art\\. \\d\\.\\R)");
        return Collections.unmodifiableList(Arrays.asList(articles));
    }

    private Integer extractArticleNumber(String article) {
        final Pattern pattern = Pattern.compile("\\d+");
        final Matcher matcher = pattern.matcher(article);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        return -1;
    }
}