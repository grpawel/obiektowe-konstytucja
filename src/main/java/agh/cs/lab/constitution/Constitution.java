package agh.cs.lab.constitution;

import javafx.util.Pair;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Paweł Grochola on 05.12.2016.
 */
public class Constitution
        implements IConstitution {
    private final List<IChapter> chapters;
    private final List<Pair<Integer, IChapter>> articleFirstNumbers;

    public Constitution(List<IChapter> chapters) {
        this.chapters = chapters;
        articleFirstNumbers = chapters.stream()
                .map(chapter -> new Pair<>(chapter.getMinArticleNo(), chapter))
                .collect(Collectors.toList());
    }

    @Override
    public List<IChapter> getChapters() {
        return Collections.unmodifiableList(chapters);
    }

    @Override
    public IChapter getChapter(Integer chapterNo) {
        return chapters.stream()
                .filter(chapter -> chapter.getChapterNo().equals(chapterNo))
                .findFirst()
                .orElse(null);
    }

    @Override
    public IArticle getArticle(Integer articleNo) {
        int chapterIndex = 0;
        while (chapterIndex < articleFirstNumbers.size() - 1
                && articleFirstNumbers.get(chapterIndex + 1).getKey() > articleNo) {
            chapterIndex++;
        }
        return articleFirstNumbers.get(chapterIndex).getValue().getArticle(articleNo);
    }

    @Override
    public List<IArticle> getArticles(List<Integer> articleNos) {
        return articleNos.stream().map(this::getArticle).collect(Collectors.toList());
    }
}
