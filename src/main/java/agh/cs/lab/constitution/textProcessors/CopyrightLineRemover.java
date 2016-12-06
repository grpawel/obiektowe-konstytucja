package agh.cs.lab.constitution.textProcessors;

import java.util.regex.Pattern;

/**
 * Created by Paweł Grochola on 05.12.2016.
 */
public class CopyrightLineRemover
        implements ITextProcessor {
    private static final Pattern copyrightSignLine = Pattern.compile("©.*\\R");
    private static final Pattern copyrightSignLineAtTheEnd = Pattern.compile("\\R©.*$");

    @Override
    public String process(String inputString) {
        String result;
        // remove lines starting with '©'
        result = copyrightSignLine.matcher(inputString).replaceAll("");
        result = copyrightSignLineAtTheEnd.matcher(result).replaceAll("");

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

