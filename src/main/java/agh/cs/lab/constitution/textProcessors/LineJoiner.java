package agh.cs.lab.constitution.textProcessors;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Paweł Grochola on 05.12.2016.
 */
public class LineJoiner
        implements ITextProcessor {
    private static final Pattern endOfLineHyphen = Pattern.compile("-\\R");

    private static final Pattern endOfLineWithoutNumber = Pattern.compile("\\R[^0-9]");

    @Override
    public String process(String inputString) {
        String result;
        // remove hyphens at the end of line
        result = endOfLineHyphen.matcher(inputString).replaceAll("");

        // remove end-of-line characters ignoring lines that start with a number
        StringBuilder resultBuilder = new StringBuilder(result);
        List<MatchResult> allMatches = new ArrayList<>();
        //find all end-of-line characters that are to be removed
        Matcher matcher = endOfLineWithoutNumber.matcher(result);
        while (matcher.find()) {
            MatchResult matchResult = matcher.toMatchResult();
            allMatches.add(matchResult);
        }
        //replace them with spaces them starting with last one
        for (int im = allMatches.size() - 1; im >= 0; im--) {
            MatchResult matchResult = allMatches.get(im);
            //-1 because regex matches also first character from next line
            resultBuilder.replace(matchResult.start(), matchResult.end() - 1, " ");
        }
        return resultBuilder.toString();
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
