package agh.cs.lab.constitution;

/**
 * Created by Paweł Grochola on 02.12.2016.
 */
public interface ITextProcessor {
    /**
     * Processes text using internal rules.
     * @param inputString Text to be processed.
     * @return Processed text.
     */
    String process(String inputString);
}
