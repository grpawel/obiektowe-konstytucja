package agh.cs.lab.constitution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class IntervalParser
        implements IIntervalParser {
    @Override
    public List<Integer> parse(String numbers) {
        String cleanNumbers = numbers.replaceAll("[^0-9,-]", "");
        String[] independentTokens = cleanNumbers.split(",");
        List<Integer> parsedNumbers = new ArrayList<>();
        for (String token : independentTokens) {
            try {
                if (token.indexOf('-') != -1) {
                    parsedNumbers.addAll(parseTokensWithRange(token));
                } else {
                    parsedNumbers.add(Integer.parseUnsignedInt(token));
                }
            } catch(NumberFormatException e) {
                //pass;
            }
        }
        return parsedNumbers;
    }

    private List<Integer> parseTokensWithRange(String token) {
        final String[] tokens = token.split("-");
        if(tokens.length > 2) {
            throw new IllegalArgumentException("Too many numbers in expression \"" + token + "\"");
        }
        final Integer firstNumber = Integer.parseInt(tokens[0]);
        final Integer secondNumber = Integer.parseInt(tokens[1]);
        if(firstNumber > secondNumber) {
            throw new IllegalArgumentException("First number cannot be bigger than second in expression \"" + token + "\"");
        }
        final List<Integer> parsedTokens = new ArrayList<>();
        Integer currentNumber = firstNumber;
        while(!currentNumber.equals( secondNumber)) {
            parsedTokens.add(currentNumber);
            currentNumber++;
        }
        parsedTokens.add(secondNumber);
        return parsedTokens;
    }
}
