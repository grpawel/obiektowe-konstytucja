package agh.cs.lab.constitution;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Paweł Grochola on 04.12.2016.
 */
public class Chapter
    implements IChapter
{
    private final Integer chapterNo;
    private final String contents;
    private final String title;
    private final Map<Integer, IArticle> articles;
    private final Integer minArticleNo;
    private final Integer maxArticleNo;

    public Chapter(Integer chapterNo, String contents, String title, List<IArticle> articleList) {
        this.chapterNo = chapterNo;
        this.contents = contents;
        this.title = title;
        this.articles = articleList.stream().collect(Collectors.toMap(IArticle::getArticleNo, i -> i));
        this.minArticleNo = Collections.min(this.articles.keySet());
        this.maxArticleNo = Collections.max(this.articles.keySet());
    }

    @Override
    public Integer getChapterNo() {
        return chapterNo;
    }

    @Override
    public String getContents() {
        return contents;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public Collection<IArticle> getArticles() {
        return articles.values();
    }

    @Override
    public IArticle getArticle(Integer articleNo) {
        return articles.get(articleNo);
    }

    @Override
    public Integer getMinArticleNo() {
        return minArticleNo;
    }

    @Override
    public Integer getMaxArticleNo() {
        return maxArticleNo;
    }


}
