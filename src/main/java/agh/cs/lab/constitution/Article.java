package agh.cs.lab.constitution;

/**
 * Created by Paweł Grochola on 04.12.2016.
 */
public class Article
    implements IArticle
{
    private final String contents;
    private final Integer articleNo;

    public Article(String contents, Integer articleNo) {
        this.contents = contents;
        this.articleNo = articleNo;
    }

    @Override
    public Integer getArticleNo() {
        return this.articleNo;
    }

    @Override
    public String getContents() {
        return this.contents;
    }

    @Override
    public String toString() {
        return "Art. " +
                articleNo +
                '.' +
                System.lineSeparator() +
                contents;
    }
}
