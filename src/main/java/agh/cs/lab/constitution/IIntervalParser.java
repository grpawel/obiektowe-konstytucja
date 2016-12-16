package agh.cs.lab.constitution;

import java.util.List;

/**
 * Created by Paweł Grochola on 02.12.2016.
 */
public interface IIntervalParser {
    /**
     * Extracts numbers contained within string.
     * @param numbers String with numbers and intervals.
     * @return List of individual numbers.
     */
    List<Integer> parse(String numbers) throws IncorrectIntervalException;
}
