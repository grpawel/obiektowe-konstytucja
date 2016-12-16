package agh.cs.lab.constitution;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class IntervalParser
        implements IIntervalParser {
    @Override
    /**
     * Doesn't work with negative numbers.
     */
    public List<Integer> parse(String numbers) throws IncorrectIntervalException {
        String incorrectCharacters = numbers.replaceAll("[ 0-9,-]", "");
        if(!incorrectCharacters.equals("")) {
            throw new IncorrectIntervalException("Incorrect characters \"" + incorrectCharacters + "\" in expression \"" + numbers + "\".");
        }
        String[] independentTokens = numbers.split("[, ]");
        List<Integer> parsedNumbers = new ArrayList<>();
        for (String token : independentTokens) {
            try {
                if (token.indexOf('-') != -1) {
                    parsedNumbers.addAll(parseTokensWithRange(token));
                } else {
                    parsedNumbers.add(Integer.parseUnsignedInt(token));
                }
            } catch(NumberFormatException e) {
                //pass
            }
        }
        return parsedNumbers;
    }

    private List<Integer> parseTokensWithRange(String token) throws IncorrectIntervalException {
        final String[] tokens = token.split("-");
        if(tokens.length > 2) {
            throw new IncorrectIntervalException("Too many numbers in expression \"" + token + "\"");
        }
        if(tokens.length <= 1 || tokens[0].equals("") || tokens[1].equals("")) {
            throw new IncorrectIntervalException("Expression \"" + token + "\" should contain two numbers.");
        }
        final Integer firstNumber = Integer.parseInt(tokens[0]);
        final Integer secondNumber = Integer.parseInt(tokens[1]);
        if(firstNumber > secondNumber) {
            throw new IncorrectIntervalException("First number cannot be bigger than second in expression \"" + token + "\"");
        }
        final List<Integer> parsedTokens = new ArrayList<>();
        Integer currentNumber = firstNumber;
        while(!currentNumber.equals(secondNumber)) {
            parsedTokens.add(currentNumber);
            currentNumber++;
        }
        parsedTokens.add(secondNumber);
        return parsedTokens;
    }
}
