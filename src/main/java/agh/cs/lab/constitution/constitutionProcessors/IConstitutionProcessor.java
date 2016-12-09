package agh.cs.lab.constitution.constitutionProcessors;

import agh.cs.lab.constitution.IConstitution;
import agh.cs.lab.constitution.textProcessors.ITextProcessor;

/**
 * Created by Paweł Grochola on 02.12.2016.
 */
public interface IConstitutionProcessor {
    /**
     * Adds text processor to use on constitution string.
     * Processors are used in the same order they were added.
     * @param textProcessor Text processor for constitution string.
     */
    void addTextProcessor(ITextProcessor textProcessor);

    /**
     * Applies all formerly added processors and returns processed text.
     * @return Processed constitution string.
     */
    IConstitution processText(String constitutionText);
}
