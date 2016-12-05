package agh.cs.lab.constitution.textProcessors;

import java.util.regex.Pattern;

/**
 * Created by Paweł Grochola on 05.12.2016.
 */
public class CopyrightLineRemover
        implements ITextProcessor {
    private static final Pattern copyrightCharLine = Pattern.compile("©.*\\R",
            Pattern.CASE_INSENSITIVE);

    @Override
    public String process(String inputString) {
        String result;
        // remove lines starting with '©'
        result = copyrightCharLine.matcher(inputString).replaceAll("");

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

