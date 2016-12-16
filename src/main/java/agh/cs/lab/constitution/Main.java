package agh.cs.lab.constitution;

import agh.cs.lab.constitution.constitutionProcessors.ConstitutionProcessor;
import agh.cs.lab.constitution.constitutionProcessors.IConstitutionProcessor;
import agh.cs.lab.constitution.constitutionProcessors.ISplitterFactory;
import agh.cs.lab.constitution.constitutionProcessors.SplitterFactory;
import agh.cs.lab.constitution.textProcessors.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class Main {
    public static void main(String args[]) {
        try {
            if(args.length < 2) {
                System.out.println("Please use correct arguments: path to file with constitution and requested articles.");
                return;
            }
            String constitutionFilePath = args[0];
            String constitutionText = readFile(constitutionFilePath, StandardCharsets.UTF_8);


            List<ITextProcessor> textProcessors = new ArrayList<>();
            textProcessors.add(new EverythingBeforeFirstChapterRemover());
            textProcessors.add(new CopyrightLineRemover());
            textProcessors.add(new DateLineRemover());
            textProcessors.add(new CapitalizedLineRemover());
            textProcessors.add(new LineJoiner());

            IConstitutionProcessor constitutionProcessor = new ConstitutionProcessor(new SplitterFactory());
            textProcessors.forEach(constitutionProcessor::addTextProcessor);
            IConstitution constitution = constitutionProcessor.processText(constitutionText);

            System.out.println(new Main().parseArguments(constitution, Arrays.copyOfRange(args, 1, args.length)));
        } catch(NoSuchFileException e) {
            System.out.println("File does not exist: " + e.getMessage());
        } catch (IOException | ObjectNotFoundException | IncorrectIntervalException e) {
            System.out.println(e.getMessage());
        }
    }

    private String parseArguments(IConstitution constitution, String[] args) throws ObjectNotFoundException, IncorrectIntervalException {
        String result = "";

        for (String arg : args) {
            boolean isChapter = true;
            Integer possibleRequestedChapter;
            try {
                possibleRequestedChapter = new RomanNumeralConverter().convertToDecimal(arg);

                Optional<IChapter> chapter = constitution.getChapter(possibleRequestedChapter);
                result += chapter.orElseThrow(() -> new ObjectNotFoundException("Chapter " + arg + " not found."));
            } catch(NumberFormatException e) {
                isChapter = false;
            }
            if(!isChapter) {
                List<Integer> requestedArticleNumbers = new IntervalParser().parse(arg);
                List<Integer> notExistingArticles = constitution.filterNotExistingArticleNumbers(requestedArticleNumbers);
                if(!notExistingArticles.isEmpty()) {
                    StringBuilder sb = new StringBuilder(notExistingArticles.size());
                    notExistingArticles.forEach(integer -> sb.append(", ").append(integer));
                    sb.replace(0,1,"");
                    throw new ObjectNotFoundException("Article(s)" + sb + " not found.");
                }
                List<IArticle> requestedArticles = constitution.getArticles(requestedArticleNumbers);
                for (IArticle article : requestedArticles) {
                    result += article;
                }
            }
        }
        return result;
    }


    private static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
