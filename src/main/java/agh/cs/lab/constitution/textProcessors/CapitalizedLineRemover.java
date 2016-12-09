package agh.cs.lab.constitution.textProcessors;

import java.util.regex.Pattern;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class CapitalizedLineRemover
    implements ITextProcessor {

    @Override
    public String process(String inputString) {
        StringBuilder result = new StringBuilder();
        Boolean shouldLineBeAdded = false;
        int lastPosition = 0;
        for (int i = 0; i < inputString.length(); i++) {
            Character c = inputString.charAt(i);
            if(isLowerCase(c)) {
                shouldLineBeAdded = true;
            }
            if(c == '\r' || c == '\n') {
                if(shouldLineBeAdded) {
                    result.append(inputString.substring(lastPosition, i));
                }
                lastPosition = i;
                if(result.toString().length() == 0) {
                    lastPosition += 1;
                }
                shouldLineBeAdded = false;
            }
        }
        if(shouldLineBeAdded) {
            result.append(inputString.substring(lastPosition));
        }
        return result.toString();
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
