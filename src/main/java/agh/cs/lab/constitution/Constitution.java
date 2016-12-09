package agh.cs.lab.constitution;

import javafx.util.Pair;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.empty;

/**
 * Created by Paweł Grochola on 05.12.2016.
 */
public class Constitution
        implements IConstitution {
    private final List<IChapter> chapters;

    public Constitution(List<IChapter> chapters) {
        this.chapters = chapters;
        this.chapters.sort((o1, o2) -> o1.getMinArticleNo().compareTo(o2.getMinArticleNo()));
    }

    @Override
    public List<IChapter> getChapters() {
        return Collections.unmodifiableList(chapters);
    }

    @Override
    public Optional<IChapter> getChapter(Integer chapterNo) {
        return chapters.stream()
                .filter(chapter -> chapter.getChapterNo().equals(chapterNo))
                .findFirst();
    }

    @Override
    public Optional<IArticle> getArticle(Integer articleNo) {
        int chapterIndex = 0;
        boolean found = false;
        IChapter currentChapter = null;
        while (chapterIndex < chapters.size() && !found) {
            currentChapter = chapters.get(chapterIndex);
            if(currentChapter.getMinArticleNo() <= articleNo && articleNo <= currentChapter.getMaxArticleNo()) {
                found = true;
            }
            else {
                chapterIndex++;
            }
        }
        if(found) {
            return currentChapter.getArticle(articleNo);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public List<IArticle> getArticles(List<Integer> articleNos) {
        return articleNos.stream()
                .map(this::getArticle)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
