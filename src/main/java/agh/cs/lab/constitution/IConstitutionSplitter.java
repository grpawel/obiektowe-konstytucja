package agh.cs.lab.constitution;

/**
 * Created by Paweł Grochola on 02.12.2016.
 */
public interface IConstitutionSplitter {
    /**
     * Splits string with constitution into structure of chapters and articles.
     * @param constitutionText String with whole constitution to split.
     * @return Structure with chapters and articles.
     */
    IConstitution split(String constitutionText);
}
