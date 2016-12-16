package agh.cs.lab.constitution.constitutionProcessors;

import agh.cs.lab.constitution.*;
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

    private final String constitutionText;
    private List<String> chapterTexts;

    public ConstitutionSplitter(String constitutionText) {
        this.constitutionText = constitutionText;
    }

    @Override
    public void split() {
        this.chapterTexts = splitIntoChapters(constitutionText);
    }

    @Override
    public List<String> getChapterTexts() {
        return chapterTexts;
    }

    private List<String> splitIntoChapters(String constitution) {
        String[] chapters = constitution.split("(?=Rozdział [IVXLCDM]+\\R)");
        return Arrays.asList(chapters);
    }
}