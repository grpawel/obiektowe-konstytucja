package agh.cs.lab.constitution;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paweł Grochola on 06.12.2016.
 */
public class RomanNumeralConverter {
    private static final Map<Character, Integer> romanDigits;
    static {
        Map<Character, Integer> digits = new HashMap<>();
        digits.put('I', 1);
        digits.put('V', 5);
        digits.put('X', 10);
        digits.put('L', 50);
        digits.put('C', 100);
        digits.put('D', 500);
        digits.put('M', 1000);
        romanDigits = Collections.unmodifiableMap(digits);
    }

    public Integer convertToDecimal(String romanNumber) {
        Integer previousValue = 0;
        Integer totalValue = 0;
        for (int i = romanNumber.length() - 1; i >= 0 ; i--) {
            Character currentChar = romanNumber.charAt(i);
            Integer currentValue = romanDigits.get(Character.toUpperCase(currentChar));
            if(currentValue == null) {
                throw new NumberFormatException("Invalid character '" + currentChar + "' found when parsing roman numeral \"" + romanNumber + "\"");
            }
            if(currentValue >= previousValue) {
                totalValue += currentValue;
                previousValue = currentValue;
            }
            else {
                totalValue -= currentValue;
            }

        }
        return totalValue;
    }
}
