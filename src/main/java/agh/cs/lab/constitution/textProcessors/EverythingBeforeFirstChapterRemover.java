package agh.cs.lab.constitution.textProcessors;

import javafx.util.Pair;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class EverythingBeforeFirstChapterRemover
        implements ITextProcessor {

    @Override
    public String process(String inputString) {
        final Pattern pattern = Pattern.compile("Rozdział [IXLCDM]");
        final Matcher matcher = pattern.matcher(inputString);
        if (matcher.find()) {
            return inputString.substring(matcher.start());
        }
        return inputString;
    }

    @Override
    public Boolean isApplicableForWholeText() {
        return true;
    }

    @Override
    public Boolean isApplicableForTextPart() {
        return false;
    }
}
