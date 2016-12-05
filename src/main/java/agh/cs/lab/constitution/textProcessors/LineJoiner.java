package agh.cs.lab.constitution.textProcessors;

import java.util.regex.Pattern;

/**
 * Created by Paweł Grochola on 05.12.2016.
 */
public class LineJoiner
        implements ITextProcessor {
    private static final Pattern endOfLineHyphen = Pattern.compile("-\\R",
            Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    private static final Pattern endOfLineWithoutNumber = Pattern.compile("\\R[^0-9]",
            Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    @Override
    public String process(String inputString) {
        String result;
        // remove hyphens at the end of line
        result = endOfLineHyphen.matcher(inputString).replaceAll("");
        // remove end-of-line characters ignoring lines that start with a number
        result = endOfLineWithoutNumber.matcher(result).replaceAll(" ");

        return result;
    }

    @Override
    public Boolean isApplicableForWholeText() {
        return false;
    }

    @Override
    public Boolean isApplicableForTextPart() {
        return true;
    }
}
