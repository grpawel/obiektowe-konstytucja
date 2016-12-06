package agh.cs.lab.constitution.textProcessors;

import java.util.regex.Pattern;

/**
 * Created by Paweł Grochola on 06.12.2016.
 */
public class DateLineRemover
        implements ITextProcessor {
    private static final Pattern dateLine = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s*\\R");
    private static final Pattern dateLineEndOfString = Pattern.compile("\\R\\d{4}-\\d{2}-\\d{2}\\s*$");

    @Override
    public String process(String inputString) {
        String result;
        // remove lines containing date in format rrrr-mm-dd
        result = dateLine.matcher(inputString).replaceAll("");
        result = dateLineEndOfString.matcher(result).replaceAll("");

        return result;
    }

    @Override
    public Boolean isApplicableForWholeText() {
        return true;
    }

    @Override
    public Boolean isApplicableForTextPart() {
        return true;
    }
}
