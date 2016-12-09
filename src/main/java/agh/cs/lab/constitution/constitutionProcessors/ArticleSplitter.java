package agh.cs.lab.constitution.constitutionProcessors;

import javafx.util.Pair;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class ArticleSplitter
        implements IArticleSplitter {
    private final String articleText;
    private String firstLine;
    private String articleContent;
    private Integer articleNumber;

    public ArticleSplitter(String articleText) {
        this.articleText = articleText;
    }

    @Override
    public void split() {
        Pair<String, String> dividedArticle = extractFirstLine(articleText);
        this.firstLine = dividedArticle.getKey();
        this.articleContent = dividedArticle.getValue();
        this.articleNumber = extractArticleNumber(this.firstLine);
    }

    @Override
    public String getFirstLine() {
        return firstLine;
    }

    @Override
    public Integer getArticleNumber() {
        return articleNumber;
    }

    @Override
    public String getArticleContent() {
        return articleContent;
    }

    private Pair<String, String> extractFirstLine(String text) {
        String[] split = text.split("\\R", 2);
        String restOfLines = split.length < 2 ? "" : split[1];
        String firstLine = split.length < 1 ? "" : split[0];
        return new Pair<>(firstLine, restOfLines);
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